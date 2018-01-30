package by.epam.bicycle.controller.command;

import by.epam.bicycle.controller.command.admin.AddBicycleCommand;
import by.epam.bicycle.controller.command.admin.BicycleCommand;
import by.epam.bicycle.controller.command.admin.DeleteBicycleCommand;
import by.epam.bicycle.controller.command.admin.UpdateBicycleCommand;
import by.epam.bicycle.controller.command.admin.UpdateUserStatusCommand;
import by.epam.bicycle.controller.command.admin.UsersCommand;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	FILTERBICYCLES {
		{
			this.command = new FilterBicyclesCommand();
		}
	},
	PREPAREORDER {
		{
			this.command = new PrepareOrderCommand();
		}
	},
	CREATEORDER {
		{
			this.command = new CreateOrderCommand();
		}
	}, 
	CHANGELANGUAGE {
		{
			this.command = new ChangeLanguageCommand();
		}
	},
	CREATEUSER {
		{
			this.command = new CreateUserCommand();
		}
	},
	RENTS {
		{
			this.command = new RentsCommand();
		}
	},
	RETURNBICYCLE {
		{
			this.command = new ReturnBicycleCommand();
		}
	},
	CLOSERENT {
		{
			this.command = new CloseRentCommand();
		}
	},
	USERS {
		{
			this.command = new UsersCommand();
		}
	},
	UPDATEUSERSTATUS {
		{
			this.command = new UpdateUserStatusCommand();
		}
	},
	BICYCLE {
		{
			this.command = new BicycleCommand();
		}
	},
	ADDBICYCLE {
		{
			this.command = new AddBicycleCommand();
		}
	},
	UPDATEBICYCLE {
		{
			this.command = new UpdateBicycleCommand();
		}
	},
	DELETEBICYCLE {
		{
			this.command = new DeleteBicycleCommand();
		}
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
