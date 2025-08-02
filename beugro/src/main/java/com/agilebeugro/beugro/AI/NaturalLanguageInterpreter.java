package com.agilebeugro.beugro.AI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.lang.reflect.Method;
import java.util.*;

@ShellComponent
public class NaturalLanguageInterpreter {

    private final OpenAIService openAIService;
    private final List<Object> shellComponents;

    @Autowired
    public NaturalLanguageInterpreter(OpenAIService openAIService, List<Object> shellComponents) {
        this.openAIService = openAIService;
        this.shellComponents = shellComponents;
    }

    @ShellMethod(key = "simulation", value = "Generates random value for the tables")
    public void simulation() {
        prompt("Please generat value for the tables that you like: user, application, background, group");
    }

    @ShellMethod(key = "prompt", value = "Prompting to the ai")
    public void prompt(String input) {
        String command = openAIService.prompt(input);

        List<CommandStorage> commands = this.commandFormatter(command);

        this.methodReflection(commands);

    }

    public List<CommandStorage> commandFormatter(String command){

        String[] commandParts = command.split("; ");
        List<CommandStorage> commandList = new ArrayList<>();

        for (String commandPart : commandParts) {
//            System.out.println(commandPart);
            String[] parts = commandPart.split(": ");
            if (parts.length > 1) {
                System.out.println(parts[0] + " " + parts[1]);
                parts[1] = parts[1].replaceAll(";", "");
                String[] args = parts[1].split(" ");
                commandList.add(new CommandStorage(parts[0], args));
            }else {
                parts[0] = parts[0].replace(";", "");
                commandList.add(new CommandStorage(parts[0], new String[]{}));
            }
        }
        return commandList;
    }

    public void methodReflection(List<CommandStorage> commands) {
        for (CommandStorage cmd : commands) {
//            System.out.println("Selected Command: " + cmd.getCommand() + ": " + cmd.getArguments().toString());
            for (Object component : shellComponents) {
                Method[] methods = component.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    if (method.getName().equals(cmd.getCommand())) {
                        try {
                            Object result = method.invoke(component, (Object[]) cmd.getArguments());
                        } catch (Exception e) {
                            System.out.println("Error invoking method: " + e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
