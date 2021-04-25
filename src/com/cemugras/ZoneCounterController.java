package com.cemugras;


import com.cemugras.interfaces.MapInterface;

public class ZoneCounterController implements com.cemugras.interfaces.ZoneCounterInterface {
    public MapController mapController;
    int zoneCount = 0;

    @Override
    public void Init(MapInterface map) throws Exception {
        this.mapController = (MapController) map;
    }

    @Override
    public int Solve() throws Exception {
        return zoneCount;
    }


    // Marker method
    public void ZoneMarker(MapController mapController, Dimension dimension) throws Exception {
        int counter = 2;

        for(int y = 0; y < dimension.height; y++){
            // Standard left -> right rotation
            for(int x = 0; x < dimension.width; x++){
                // Start point of given area
                if(x == 0 && y == 0){
                    mapController.MarkCoordinate(x, y, 2);
                // All left y axis (x=0 and y>0)
                }else if(x == 0 && mapController.GetVar(x, y) == 0 && mapController.GetVar(x, y-1) != 0){
                    if(mapController.GetVar(x, y-1) != 1)
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x, y-1));
                    // Upper point is a border -> set cursor point to a new upper value
                    else {
                        counter++;
                        mapController.MarkCoordinate(x, y, counter);
                    }
                // All top x axis (x>0 and y=0)
                }else if(y == 0 && mapController.GetVar(x, y) == 0) {
                    // Left box is not 1
                    if (mapController.GetVar(x - 1, y) != 1) {
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x - 1, y));
                        // Left box is 1
                    } else {
                        counter++;
                        mapController.MarkCoordinate(x, y, counter);
                    }
                // Standard matrix left->right path, cursor=0 and not a top or left box
                }else if(mapController.GetVar(x, y) == 0){
                    // Left box is not 0 and not 1 -> assign left box to cursor
                    if(mapController.GetVar(x-1, y) != 0 && mapController.GetVar(x-1, y) != 1){
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x-1, y));
                    // Upper box is not 0 and not 1 -> assign upper box to cursor
                    }else if(mapController.GetVar(x, y-1) != 0 && mapController.GetVar(x, y-1) != 1){
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x, y-1));
                    // Right box is not 0 and not 1 -> assign right box to cursor
                    }else if(x + 1 != dimension.width && mapController.GetVar(x+1, y) != 0 && mapController.GetVar(x+1, y) != 1){
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x+1, y));
                    // Lower box is not 0 and not 1 -> assign lower box to cursor
                    }else if(y + 1 != dimension.height && mapController.GetVar(x, y+1) != 0 && mapController.GetVar(x, y+1) != 1) {
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x, y + 1));
                    }
                }
            }
            // Standard left <- right rotation
            for(int x = dimension.width; x > 0; x--){
                // Start point of given area when (max,max)
                if(x == dimension.width && y == dimension.height && mapController.GetVar(x,y)==0){
                    counter ++;
                    mapController.MarkCoordinate(x, y, counter);
                // All right y axis (x=max and y<max)
                }else if(x == dimension.width-1 && y > 0 && mapController.GetVar(x, y) == 0 && mapController.GetVar(x, y-1) != 0){
                    if(mapController.GetVar(x, y-1) != 1)
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x, y-1));
                    // Upper point is a border -> set cursor point to a new upper value
                    else{
                        counter++;
                        mapController.MarkCoordinate(x, y, counter);
                    }
                // All bottom x axis (x>0 and y=max)
                }else if(y == dimension.height-1 && x < dimension.width && mapController.GetVar(x, y) == 0){
                    // Right box is not 1
                    if(mapController.GetVar(x+1, y) != 1){
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x+1, y));
                    // Right box is 1
                    }else{
                        counter++;
                        mapController.MarkCoordinate(x, y, counter);
                    }
                // Standard matrix left <- right path, cursor=0 and not a top or left box
                }else if(x < dimension.width-1  && mapController.GetVar(x, y) == 0){
                    // Right box is not 0 and not 1 -> assign right box to cursor
                    if(mapController.GetVar(x+1, y) != 0 && mapController.GetVar(x+1, y) != 1){
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x+1, y));
                    // Upper box is not 0 and not 1 -> assign upper box to cursor
                    }else if(mapController.GetVar(x, y-1) != 0 && mapController.GetVar(x, y-1) != 1){
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x, y-1));
                    // Left box is not 0 and not 1 -> assign left box to cursor
                    }else if(x - 1 != 0 && mapController.GetVar(x-1, y) != 0 && mapController.GetVar(x-1, y) != 1){
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x-1, y));
                    // Lower box is not 0 and not 1 -> assign lower box to cursor
                    }else if(y - 1 != 0 && mapController.GetVar(x, y+1) != 0 && mapController.GetVar(x, y+1) != 1) {
                        mapController.MarkCoordinate(x, y, mapController.GetVar(x, y+1));
                    }
                }
            }
        }

        zoneCount = mapController.ZoneCounter();
    }
}
