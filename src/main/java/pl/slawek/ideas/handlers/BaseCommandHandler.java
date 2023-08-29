package pl.slawek.ideas.handlers;

abstract class BaseCommandHandler implements CommandHandler {

    @Override
    public boolean supports(final String name) {
        return getCommandName().equals(name);
    }

    protected abstract String getCommandName();
}
