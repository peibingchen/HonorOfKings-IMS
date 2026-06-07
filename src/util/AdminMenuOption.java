package util;

public enum AdminMenuOption {
    ADD_PLAYER("1", "Add player"),
    EDIT_PLAYER("2", "Edit player"),
    DELETE_PLAYER("3", "Delete player"),
    ADD_HERO("4", "Add hero"),
    EDIT_HERO("5", "Edit hero"),
    DELETE_HERO("6", "Delete hero"),
    ADD_EQUIPMENT("7", "Add equipment"),
    EDIT_EQUIPMENT("8", "Edit equipment"),
    DELETE_EQUIPMENT("9", "Delete equipment"),
    ADD_TEAM("10", "Add team"),
    EDIT_TEAM("11", "Edit team"),
    DELETE_TEAM("12", "Delete team"),
    ADD_MATCH_RECORD("13", "Add match record"),
    EDIT_MATCH_RECORD("14", "Edit match record"),
    DELETE_MATCH_RECORD("15", "Delete match record"),
    ADD_OR_UPDATE_MATCH_PICK("16", "Add or update match hero pick"),
    CLEAR_MATCH_PICKS("17", "Clear match hero picks"),
    BACK("0", "Back");

    private final String code;
    private final String label;

    AdminMenuOption(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static AdminMenuOption fromCode(String code) {
        for (AdminMenuOption option : values()) {
            if (option.code.equals(code)) {
                return option;
            }
        }
        return null;
    }
}
