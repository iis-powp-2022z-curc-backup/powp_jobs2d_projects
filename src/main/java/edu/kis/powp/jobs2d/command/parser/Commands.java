package edu.kis.powp.jobs2d.command.parser;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Commands {
    @JsonProperty("commands")
    private final List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = commands;
    }

    public List<Command> getCommands() {
        return this.commands;
    }
}
