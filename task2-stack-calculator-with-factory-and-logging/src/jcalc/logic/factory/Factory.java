package jcalc.logic.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jcalc.logic.cmd.Command;

public class Factory { // TODO: use real factory design pattern - not a simple factory
    private Map<String, String> classMap = new HashMap<>();

    public Factory() {
        loadClasses();
    }

    private void loadClasses() {
        try (InputStream is = Factory.class.getResourceAsStream("CommandList.properties")) {
            if (is == null) {
                throw new IllegalStateException(
                        "Configuration file 'CommandList.properties' was not founded in pacgake "
                                + Factory.class.getPackageName());
            }

            Properties props = new Properties();
            props.load(is);

            for (String key : props.stringPropertyNames()) {
                String className = props.getProperty(key);
                if (!key.isEmpty() && !className.isEmpty()) {
                    String keyLowerCase = key.toLowerCase();
                    if (key != keyLowerCase) {
                        classMap.put(keyLowerCase, className);
                    }
                    classMap.put(key, className);
                }
            }

        } catch (IOException e) {
            throw new IllegalStateException("Failed to load configuration");
        }
    }

    public Command newCommand(String cmdName) throws IllegalArgumentException {
        if (cmdName == null || cmdName.isEmpty()) {
            throw new IllegalArgumentException("Command name cannot be null or empty");
        }

        // if it is a comment
        if (cmdName.charAt(0) == '#') {
            cmdName = "#";
        }

        String className = classMap.get(cmdName);

        if (className == null) {
            throw new IllegalArgumentException("Unknown command: " + cmdName);
        }
        try {
            Class<?> clazz = Class.forName(className);
            if (!Command.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException("Class" + className + "does not implement Command interface");
            }
            return (Command) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(
                    "Comand '" + cmdName + "' is in config, but class " + className + " does not exist");
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unfixable problems while instantiate command class");
        }

    }
}
