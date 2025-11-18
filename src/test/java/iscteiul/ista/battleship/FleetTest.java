package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest {

    @Test
    @DisplayName("Should return the list of ships")
    void testListingOfShips() {
        Carrack carrack = new Carrack(Compass.NORTH, new Position(2, 1));
        Barge barge = new Barge(Compass.NORTH, new Position(5, 7));
        Caravel caravel = new Caravel(Compass.NORTH, new Position(3, 5));

        Fleet fleet = new Fleet();
        fleet.addShip(carrack);
        fleet.addShip(barge);
        fleet.addShip(caravel);

        List<Ship> list = new ArrayList<>();
        list.add(carrack);
        list.add(barge);
        list.add(caravel);

        assertEquals(list, fleet.getShips());
    }

    @Test
    @DisplayName("Test if ship is added to fleet")
    void testAddShip() {
        Carrack carrack = new Carrack(Compass.NORTH, new Position(5, 5));
        Fleet fleet = new Fleet();

        assertEquals(true, fleet.addShip(carrack));
    }

}