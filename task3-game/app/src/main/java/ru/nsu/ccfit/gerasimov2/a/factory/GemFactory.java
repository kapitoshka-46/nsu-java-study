package ru.nsu.ccfit.gerasimov2.a.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import ru.nsu.ccfit.gerasimov2.a.exception.FactoryException;
import ru.nsu.ccfit.gerasimov2.a.gem.Gem;
import ru.nsu.ccfit.gerasimov2.a.gem.GemDestroyer;


public class GemFactory {
    private Map<String, String> classMap;

    private static void mapProperties(InputStream is, Map<String, String> map) throws IOException {
        if (is == null) { throw new IllegalStateException("Failed to open file 'factory.properties'."); }

        Properties props = new Properties();
        props.load(is);
        
        for (String name : props.stringPropertyNames()) {
            String className = props.getProperty(name);
            if (name.isEmpty() || className.isEmpty()) {
                System.out.printf("[Waring] Empty property: %s=%s\n", name, className);
                continue;
            }
            map.put(name, className);
        }
    }

    public GemFactory() {
        loadClasses();
    }

    private void loadClasses() {
        try (InputStream is = GemFactory.class.getResourceAsStream("factory.properties")) {
            mapProperties(is, classMap);
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to load configuraion: " + e.getLocalizedMessage());
        }
    }
    // TODO: why there 2 methods with almost the same functionality bro wtf????
    public Gem newGem(String name) {
        if (name.isEmpty()) { throw new IllegalArgumentException("Emptyname"); }
        String className = classMap.get(name + "Destroyer"); 
        if (className == null) { throw new FactoryException("Unknown Gem name: " + name); }
        
        try {
            Class<?> clazz = Class.forName(name);
            boolean isChildOfClass = Gem.class.isAssignableFrom(clazz);
            if (!isChildOfClass) { throw new FactoryException(className + " does not *extends* Gem class"); }
            return (Gem) clazz.getDeclaredConstructor().newInstance();
        }
        catch (ClassNotFoundException e) { throw new FactoryException("class" + className + "not found"); }
        catch (ReflectiveOperationException e) { throw new FactoryException("reflective operations exception: " + e.getLocalizedMessage()); }
    }

    public GemDestroyer newGemDestroyer(String name)  {
        if (name.isEmpty()) { throw new IllegalArgumentException("Emptyname"); }
        String className = classMap.get(name + "Destroyer");                    /* difference from the top method!!!!! */
        if (className == null) { throw new FactoryException("Unknown Gem name: " + name); }
        
        try {
            Class<?> clazz = Class.forName(name);
            boolean isChildOfClass = GemDestroyer.class.isAssignableFrom(clazz);
            if (!isChildOfClass) { throw new FactoryException(className + " does not *extends* Gem class"); }
            return (GemDestroyer) clazz.getDeclaredConstructor().newInstance();
        }
        catch (ClassNotFoundException e) { throw new FactoryException("class" + className + "not found"); }
        catch (ReflectiveOperationException e) { throw new FactoryException("reflective operations exception: " + e.getLocalizedMessage()); }
    }
}
