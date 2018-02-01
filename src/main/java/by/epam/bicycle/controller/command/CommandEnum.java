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
