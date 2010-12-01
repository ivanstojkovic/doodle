package at.tuwien.sbc.feeder.common;

public final class Constants {
    
    private static final String CMD_MENU_LOGIN = "menu.login";
    
    public static final String CMD_MENU_LOGOUT = "menu.logout";
    
    public static final String CMD_MENU_QUIT = "menu.quit";
    
    public static final String CMD_MENU_CLEAR = "menu.clear";
    
    public static final String CMD_MENU_EXIT = Constants.CMD_MENU_QUIT;
    
    public static final String CMD_MENU_HELP = "menu.help";
    
    public static final String CMD_MENU_ABOUT = "menu.about";
    
    public static final String CMD_BTN_LOGIN = "credentials.login";
    
    public static final String CMD_BTN_REGISTER = "credentials.register";
    
    public static final String CMD_BTN_CANCEL = "credentials.cancel";
    
    public static final String CMD_BTN_SEARCH = "application.search";
    
    public static final String CMD_BTN_SHOW = "overview.show";
    
    public static final String CMD_BTN_CREATE = "organization.create";
    
    public static final String CMD_BTN_UPDATE = "organization.update";
    
    public static final String CMD_BTN_ADD_PARTICIPANT = "organization.add_participant";
    
    public static final String CMD_BTN_REMOVE_PARTICIPANT = "organization.remove_participant";
    
    public static final String CMD_BTN_ADD_INVITATION = "organization.add_invitation";
    
    public static final String CMD_BTN_REMOVE_INVITATION = "organization.remove_invitation";
    
    public static final String CMD_BTN_INVITATION_CONFIRM = "overview.confirm";
    
    public static final String CMD_BTN_INVITATION_REJECT = "overview.reject";

	public static String CMD_BTN_ADD_COMMENT = "overview.add_comment";

	public static String CMD_BTN_REMOVE_COMMENT = "overview.remove_comment";

	public static String CMD_EVENT_COMBO_CHANGED = "overview.event_combo_changed";
    
    private Constants() {
        
    }
}
