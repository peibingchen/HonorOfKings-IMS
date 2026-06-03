package model;

public abstract class Person {
    private final String id;
    private String name;
    private String password;
    private final Role role;

    protected Person(String id, String name, String password, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 3) {
            throw new IllegalArgumentException("Password must contain at least 3 characters.");
        }
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
}
