package commands;

        import java.util.Objects;

/**
 * Класс - абстракция каждой из команд
 * Реализация шаблона Abstract Class + Interface
 */
public abstract class AbstractCommand {
    private final String name;
    private final String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return Имя команды
     */
    public String getName() {
        return name;
    }

    /**
     * @return Описание команды
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCommand that = (AbstractCommand) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    public abstract void execute(String[] args);
}