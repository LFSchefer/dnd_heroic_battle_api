pipeline {
    agent {
        label "${node}"
    }
    tools {
        jdk "JDK21"
        maven "Default"
    }
    stages {
        stage("Clean & Clone") {
            steps {
                sh """#!/bin/bash
                set -e
                rm -rf /home/eucalyptus-jenkins-node/workspace/eucalyptus-folder/API_pipeline/*
                cd /home/eucalyptus-jenkins-node/workspace/eucalyptus-folder/API_pipeline/
                git clone 'https://github.com/LFSchefer/dnd_heroic_battle_api.git' && echo "cloned"
                """
            }
        }
        stage("Set up database for test") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'c28b396d-485d-429b-8a11-d32cf4b73b3c', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh """#!/bin/bash
                    psql postgresql://\$USERNAME:\$PASSWORD@localhost:5432/eucalyptus <<MULTILINE
                    \\set ON_ERROR_STOP
                    SET SEARCH_PATH = tests;
                    \\i /srv/readresolve.tech/eucalyptus/data/schema.ddl.sql
                    \\i /srv/readresolve.tech/eucalyptus/data/data-test.dml.sql
                    select * from users;
                    \\q
                    MULTILINE
                    """
                }
            }
        }
        stage("Build & Test") {
            steps {
                sh """#!/bin/bash
                set -e
                cd /home/eucalyptus-jenkins-node/workspace/eucalyptus-folder/API_pipeline/dnd_heroic_battle_api/src/test/resources
                mkdir test
                cd test
                cp /srv/readresolve.tech/eucalyptus/secrets/application-test.properties ./
                cd /home/eucalyptus-jenkins-node/workspace/eucalyptus-folder/API_pipeline/dnd_heroic_battle_api
                mvn -Dmaven.spring.config.import=/srv/readresolve.tech/eucalyptus/secrets/secrets.properties clean install jacoco:report
                """
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv(credentialsId: 'sqp_4139adfa9a27e6d8022907e5db47ef3e0f8a0e27') {
                sh """
                cd /home/eucalyptus-jenkins-node/workspace/eucalyptus-folder/API_pipeline/dnd_heroic_battle_api
                mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar
                """
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage("Deploy") {
            steps {
                sh """#!/bin/bash
                checkServer(){
                    response=\$(curl --silent --output /dev/null --write-out "%{http_code}" http://localhost:8089/api/import-data/ping)

                    if [ "\$response" = "200" ]; then
                        echo "\$(date "+%Y-%m-%d %H:%M:%S") - Server is healthy, up and running"
                        return 0
                    else
                        echo "\$(date "+%Y-%m-%d %H:%M:%S") - Server is not healthy (response code - \$response )"
                        return 1
                    fi
                }

                echo "Cleaning .old files"

                if [ ! -d /srv/readresolve.tech/eucalyptus/api ]; then
                    mkdir -p /srv/readresolve.tech/eucalyptus/api
                fi

                cd /srv/readresolve.tech/eucalyptus/api

                archive_files=/srv/readresolve.tech/eucalyptus/api/*.old
                if [ -e "\$files" ]; then
                    rm -r /srv/readresolve.tech/eucalyptus/api/*.old
                else
                    echo "no file .old found."
                fi

                echo "clean done"

                echo "killing old app"

                process_pid=\$(lsof -t -i :8089)

                if [ -n "\$process_pid" ]; then
                    kill -9 "\$process_pid"
                    echo "Process \$process_pid kill."
                else
                    echo "no process on port 8089."
                fi

                echo "kill done"


                echo "achiving old app"

                old_file=\$(find . -maxdepth 1 -type f -name "*.jar" | head -n 1)

                if [ -n "\$old_file" ]; then
                    mv "\$old_file" "\${old_file}.old"
                fi

                echo "archiving done"


                echo "copying .jar files from 'target' to 'api'"

                cp -r /home/eucalyptus-jenkins-node/workspace/eucalyptus-folder/API_pipeline/dnd_heroic_battle_api/target/*.jar /srv/readresolve.tech/eucalyptus/api

                echo "copying done"


                echo "launching new app"

                new_file=\$(find . -maxdepth 1 -type f -name "*.jar" | head -n 1)

                echo "\$new_file"
                echo "###########################"
                echo "Starting Spring Boot application..."
                echo "###########################"

                JENKINS_NODE_COOKIE=dontKillMe nohup java -Dspring.config.import=/srv/readresolve.tech/eucalyptus/secrets/secrets.properties -jar "\$new_file" >/dev/null &

                echo "###########################"
                echo "########  PID  ############"
                APP_PID=\$!
                echo "Application started with PID: \$APP_PID"

                TIMEOUT=120 # seconds
                ELAPSED_TIME=0

                until checkServer; do
                    if [ "\$ELAPSED_TIME" -ge "\$TIMEOUT" ]; then
                        echo "###########################"
                        echo "Server did not become healthy within \$TIMEOUT seconds. Exiting."
                        echo "###########################"

                        kill \$APP_PID
                        exit 1
                    fi
                    echo "Waiting for server to become healthy... (Elapsed: \$ELAPSED_TIME/\$TIMEOUT seconds)"
                    sleep 2
                    ELAPSED_TIME=\$((ELAPSED_TIME + 2))
                done

                echo "###########################"
                echo "Server is healthy! Build successful."
                echo "###########################"
                echo "########  CURL  ###########"
                """
            }
        }
    }
}
