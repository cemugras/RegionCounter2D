package com.cemugras;

public class MapController implements com.cemugras.interfaces.MapInterface {
    private Dimension dimension;
    private int[][] map;
    int zoneCount = 0;

    @Override
    public void SetSize(Dimension dim) {
        this.dimension = dim;
        this.map = new int[dimension.height][dimension.width];
    }

    // not needed & not used -> area is given hardcoded from main method
    // GetSize is void
    @Override
    public void GetSize(Dimension dim) {
    }

    @Override
    public void SetBorder(int x, int y) throws Exception {
        if(dimension.CheckWithin(x, y)) {
            map[y][x] = 1;
        }
    }

    @Override
    public void ClearBorder(int x, int y) throws Exception {
        if(dimension.CheckWithin(x, y)) {
            map[y][x] = 0;
        }
    }

    @Override
    public boolean IsBorder(int x, int y) throws Exception {
        return this.dimension.CheckWithin(x, y) && map[y][x] == 1;
    }

    // Display method of a given area as two dimensional array
    @Override
    public void Show() {
        for (int y = 0; y < dimension.height; y++) {
            for (int x = 0; x < dimension.width; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println("");
        }
    }

    // Marker method
    protected void MarkCoordinate(int x, int y, int Marker){
        map[y][x] = Marker;
    }

    // Getting variable from given coordinates
    public int GetVar(int x, int y){
        return map[y][x];
    }

    // Counter method for given area as two dimensional array
    public int ZoneCounter(){
        for (int y = 0; y < dimension.height; y++) {
            for (int x = 0; x < dimension.width; x++) {
                if (map[y][x] > zoneCount) {
                    zoneCount = map[y][x];
                }
            }
        }
        zoneCount--;

        return zoneCount;
    }

    public int[][] getMap() {
        return map;
    }
}

