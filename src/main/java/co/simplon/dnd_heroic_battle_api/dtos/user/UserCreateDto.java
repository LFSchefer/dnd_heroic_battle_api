package co.simplon.dnd_heroic_battle_api.dtos.user;

import co.simplon.dnd_heroic_battle_api.dtos.user.validators.UniqueUserCreate;
import co.simplon.dnd_heroic_battle_api.dtos.user.validators.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


@UniqueUserCreate
public record UserCreateDto(@Size(min = 5, max = 50) String userName, @Email String email, @ValidPassword String password) {

	@Override
	public String toString() {
		return "UserCreateDto [userName=" + userName + ", email=" + email + ", password=[PROTECTED] ]";
	}
	
}
