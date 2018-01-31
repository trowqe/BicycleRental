package by.epam.bicycle.controller.command;

import by.epam.bicycle.controller.command.admin.AddBicycleCommand;
import by.epam.bicycle.controller.command.common.BicycleCommand;
import by.epam.bicycle.controller.command.admin.DeleteBicycleCommand;
import by.epam.bicycle.controller.command.admin.UpdateBicycleCommand;
import by.epam.bicycle.controller.command.admin.UpdateUserStatusCommand;
import by.epam.bicycle.controller.command.admin.UsersCommand;
import by.epam.bicycle.controller.command.common.ChangeLanguageCommand;
import by.epam.bicycle.controller.command.common.FilterBicyclesCommand;
import by.epam.bicycle.controller.command.common.LoginCommand;
import by.epam.bicycle.controller.command.common.LogoutCommand;
import by.epam.bicycle.controller.command.user.CloseRentCommand;
import by.epam.bicycle.controller.command.user.CreateOrderCommand;
import by.epam.bicycle.controller.command.user.CreateUserCommand;
import by.epam.bicycle.controller.command.user.PrepareOrderCommand;
import by.epam.bicycle.controller.command.user.RentsCommand;
import by.epam.bicycle.controller.command.user.ReturnBicycleCommand;
import by.epam.bicycle.service.impl.BicycleModelService;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.BicycleTypeService;
import by.epam.bicycle.service.impl.RentService;
import by.epam.bicycle.service.impl.RentalPointService;
import by.epam.bicycle.service.impl.TariffService;
import by.epam.bicycle.service.impl.UserService;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand(new UserService());
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	FILTERBICYCLES {
		{
			this.command = new FilterBicyclesCommand(new BicycleService(), new RentalPointService(), new BicycleTypeService());
		}
	},
	PREPAREORDER {
		{
			this.command = new PrepareOrderCommand(new BicycleService(), new TariffService());
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
			this.command = new RentsCommand(new RentService());
		}
	},
	RETURNBICYCLE {
		{
			this.command = new ReturnBicycleCommand(new RentService());
		}
	},
	CLOSERENT {
		{
			this.command = new CloseRentCommand(new RentService(), new UserService());
		}
	},
	USERS {
		{
			this.command = new UsersCommand(new UserService());
		}
	},
	UPDATEUSERSTATUS {
		{
			this.command = new UpdateUserStatusCommand(new UserService());
		}
	},
	BICYCLE {
		{
			this.command = new BicycleCommand(new BicycleService(), new RentalPointService(), new BicycleModelService());
		}
	},
	ADDBICYCLE {
		{
			this.command = new AddBicycleCommand(new BicycleService());
		}
	},
	UPDATEBICYCLE {
		{
			this.command = new UpdateBicycleCommand(new BicycleService());
		}
	},
	DELETEBICYCLE {
		{
			this.command = new DeleteBicycleCommand(new BicycleService());
		}
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
