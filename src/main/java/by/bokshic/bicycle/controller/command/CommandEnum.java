package by.bokshic.bicycle.controller.command;

import by.bokshic.bicycle.controller.command.admin.*;
import by.bokshic.bicycle.controller.command.common.*;
import by.bokshic.bicycle.controller.command.user.CloseRentCommand;
import by.bokshic.bicycle.controller.command.user.CreateOrderCommand;
import by.bokshic.bicycle.controller.command.user.CreateUserCommand;
import by.bokshic.bicycle.controller.command.user.PrepareOrderCommand;
import by.bokshic.bicycle.controller.command.admin.AddBicycleCommand;
import by.bokshic.bicycle.controller.command.common.BicycleCommand;
import by.bokshic.bicycle.controller.command.admin.DeleteBicycleCommand;
import by.bokshic.bicycle.controller.command.admin.UpdateBicycleCommand;
import by.bokshic.bicycle.controller.command.admin.UpdateUserStatusCommand;
import by.bokshic.bicycle.controller.command.admin.UsersCommand;
import by.bokshic.bicycle.controller.command.common.ChangeLanguageCommand;
import by.bokshic.bicycle.controller.command.common.FilterBicyclesCommand;
import by.bokshic.bicycle.controller.command.common.LoginCommand;
import by.bokshic.bicycle.controller.command.common.LogoutCommand;
import by.bokshic.bicycle.controller.command.user.CloseRentCommand;
import by.bokshic.bicycle.controller.command.user.CreateOrderCommand;
import by.bokshic.bicycle.controller.command.user.CreateUserCommand;
import by.bokshic.bicycle.controller.command.user.PrepareOrderCommand;
import by.bokshic.bicycle.controller.command.user.RentsCommand;
import by.bokshic.bicycle.controller.command.user.ReturnBicycleCommand;
import by.bokshic.bicycle.service.impl.BicycleModelService;
import by.bokshic.bicycle.service.impl.BicycleService;
import by.bokshic.bicycle.service.impl.BicycleTypeService;
import by.bokshic.bicycle.service.impl.RentService;
import by.bokshic.bicycle.service.impl.RentalPointService;
import by.bokshic.bicycle.service.impl.TariffService;
import by.bokshic.bicycle.service.impl.UserService;

public enum CommandEnum {
	LOGIN(new LoginCommand(new UserService())),
	LOGOUT(new LogoutCommand()),
	FILTERBICYCLES(new FilterBicyclesCommand(new BicycleService(), new RentalPointService(), new BicycleTypeService())),
	PREPAREORDER(new PrepareOrderCommand(new BicycleService(), new TariffService())),
	CREATEORDER(new CreateOrderCommand()),
	CHANGELANGUAGE(new ChangeLanguageCommand()),
	CREATEUSER(new CreateUserCommand()),
	RENTS(new RentsCommand(new RentService())),
	RETURNBICYCLE(new ReturnBicycleCommand(new RentService())),
	CLOSERENT(new CloseRentCommand(new RentService(), new UserService())),
	USERS(new UsersCommand(new UserService())),
	UPDATEUSERSTATUS(new UpdateUserStatusCommand(new UserService())),
	BICYCLE(new BicycleCommand(new BicycleService(), new RentalPointService(), new BicycleModelService())),
	ADDBICYCLE(new AddBicycleCommand(new BicycleService())),
	UPDATEBICYCLE(new UpdateBicycleCommand(new BicycleService())),
	DELETEBICYCLE(new DeleteBicycleCommand(new BicycleService()));
	
	private ActionCommand command;	

	public ActionCommand getCommand() {
		return command;
	}

	public void setCommand(ActionCommand command) {
		this.command = command;
	}

	private CommandEnum(ActionCommand command) {
		this.command = command;
	}	
	

}
