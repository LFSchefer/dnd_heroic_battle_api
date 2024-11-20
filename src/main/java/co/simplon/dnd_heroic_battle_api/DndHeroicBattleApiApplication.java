package co.simplon.dnd_heroic_battle_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DndHeroicBattleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DndHeroicBattleApiApplication.class, args);
	}

}
