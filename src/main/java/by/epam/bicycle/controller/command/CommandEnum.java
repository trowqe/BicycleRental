package by.epam.bicycle.controller.command;

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
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
