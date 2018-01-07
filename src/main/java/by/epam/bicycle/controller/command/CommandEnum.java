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
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
