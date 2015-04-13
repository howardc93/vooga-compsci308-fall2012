# Introduction #

The abstract path finder provides basic function of using path finding algorithms such as depth first search, breadth first search and Dijkstra.

Examples of how it could be used in games could be found in vooga.turnbased.gamecore.graphutility.MapModePathFinder

# Details #

When using PathFinder, one could specify the method used for path finding (bfs, dfs, or potentially Dijkstra). That is, in the constructor, one could set the search algorithm by saying:
```
setPathSearch(new BreadthFirstSearch(getStart(), getEnd(), getSize()));
```
BFS is garenteed to find the shortest path from one location to another
DFS can find the path faster but is not garenteed to find the shortest one. However, improvements have been made to make sure that it finds relatively optimal path.

And in order to find the path, one could simply call addTask(parameters in the super class or subclasses, and executeSearch(); So in the turnbased sample game, in handling the right click, the two lines:
```
    myPathFinder.addTask(myPlayer, target, myMapSize);
    myPathFinder.executeSearch();
```

The PathFinder also provide highlight path functionality through the method of updatePath(). This method should be called in the update loop, so that the path could be shown in the map grid.

The PathFinder also contains two abstract classes: highlightPath(), dehighlightPath().
The subclasses of PathFinder will be responsible to override them and decide how the path found are to be shown on the map.
In the case of MapModePathFinder, highlightPath() adds MapObjects to the map showing the path, and upon the player steps on it, they disappear. DehighlightPath() simply made them invisible, and later they were deleted by the MapMode.
```
    /**
     * highlight by generating path indicators
     * Could be overridden if other ways of highlighting path are needed
     */
    @Override
    protected void highlightPath (Point position) {
        MapObject m = generatePathIndicator(position);
        m.setMapMode(myMap);
        myMap.addMapObject(position, m);
        myHighlightObjects.add(m);
    }

    @Override
    protected void dehighlightPath () {
        for (MapObject m : myHighlightObjects) {
            m.setVisible(false);
        }
    }

```

These are examples of using the methods to create graphical effects of path found

Another abstract method that needs to be overridden, checkObstacle(), which is responsible for taking existing obstacles into account. Subclasses will determine which location is blocked

MapModePathFinder's checkObstacle() is as follows:

```
    @Override
    protected void checkObstacles () {
        for (int i = 0; i < getSize().width; i++) {
            for (int j = 0; j < getSize().height; j++) {
                for (MapObject m : myMap.getSpritesOnTile(i, j)) {
                    if (m instanceof MapObstacleObject) {
                        getPathSearch().markVisited(i, j);
                    }
                }
            }
        }
    }
```