### A proposed API for converting certain objects (Sprites, Levels, etc.) into an xml representation. This will be useful for everyone who is writing level editors. ###


---


# Introduction #

This is just a proposal. The goal is to make it easier for everyone and to consolidate some of the repeated code between groups, not to make things more complicated.


# Details #

**Option 1: An Interface**

```
public interface Packable<T>() {

   // Store the object as an xml Element
   public Element pack();

   // Instantiate the object from an xml Element
   // (probably using reflection)
   public T unpack(Element xmlData);

}
```

The advantage of this is that it allows each object to specify the details of how it should be packed/unpacked. (Allows for special cases). The disadvantage is that it requires everyone to write a little bit more code. However, anyone can use the xml utilities in util.xml (which are being improved now) to help with this.

**Option 2: A Static Utility class for Packing/Unpacking**

```
public class XmlPacker {

   // Store the object as an xml Element
   public Element pack(Object o);

   // Instantiate the object from an xml Element
   // (probably using reflection)
   public Object unpack(Element xmlData);

}
```

The advantage of this is that people don't have to write much additional code to implement it. The disadvantage is that we would have to make a lot of assumptions about how the object that's being packed/unpacked. We would need to know what arguments were needed for the constructor and how to get values for those arguments (probably using getters, setters). We would need probably need to rely on naming conventions for these getters and setters.

# Additional Thoughts #

Java has a way of serializing objects, but it doesn't use xml. Maybe we can use this in our implementation?
http://www.tutorialspoint.com/java/java_serialization.htm
http://docs.oracle.com/javase/1.4.2/docs/api/java/io/ObjectOutputStream.html
http://docs.oracle.com/javase/1.4.2/docs/api/java/io/ObjectInputStream.html