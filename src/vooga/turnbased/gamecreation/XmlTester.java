package vooga.turnbased.gamecreation;

import vooga.turnbased.gameobject.MapPlayerObject;
import java.io.File;

public class XmlTester {

    /**
     * @param args
     */
    public static void main (String[] args) {
        String xmlPath = "src/vooga/turnbased/resources/Example.xml";
        File xmlFile = new File(xmlPath);
        LevelCreator level = new LevelCreator(xmlFile);
        System.out.println(level.getDocumentElement().getNodeName());
        System.out.println(level.parseBackgroundImage());
        System.out.println(level.parseDimension());
        
        MapPlayerObject mapObject = (MapPlayerObject) level.parserMapPlayer();
        System.out.println(mapObject.getID());
        System.out.println(mapObject.getImage());
        System.out.println(mapObject.getEvent());
        System.out.println(mapObject.getLocation());
//        XmlParser parser = new XmlParser(xmlFile);
//
//        Element root = parser.getDocumentElement();
//        System.out.println(root.getNodeName().toString());
//
//        NodeList dimensionList = parser.getElementsByName(root, "dimension");
//        Element dimension = (Element) dimensionList.item(0);
//        System.out.println("  " + dimension.getNodeName());
//        int width = parser.getIntContent(dimension, "width");
//        double height = parser.getDoubleContent(dimension, "height");
//        System.out.println("    width: " + width);
//        System.out.println("    height: " + height);
//
//        Image background = parser.getImageContent(root, "backgroundImage");
//        System.out.println("  Image: " + background);
//
//        NodeList eventList = parser.getElementsByName(root, "event");
//        System.out.println("  " + eventList.item(0).getNodeName());
//        for (int i = 0; i < eventList.getLength(); i++) {
//            Element event = (Element) eventList.item(i);
//            String eventName = parser.getTextContent(event, "name");
//            String eventDestination = parser.getTextContent(event, "destination");
//            System.out.println("    name: " + eventName);
//            System.out.println("    destination: " + eventDestination);
//        }
    }
}
