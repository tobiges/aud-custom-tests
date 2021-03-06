import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Hotel4TheCatCustomTest {

    private final static Random RND = new Random();

    // == Configuration ==
    // Set this value to true, if you use dynamic array allocation
    private final static boolean DYNAMIC_ALLOCATION = false;

    @Test(timeout = 50)
    public void customTest_space_constructor() {

        try {
            House4TheCat h = new House4TheCat(0, 1);
            Assert.fail("width/height zero not allowed");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            House4TheCat h = new House4TheCat(1, 0);
            Assert.fail("width/height zero not allowed");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            Room4TheCat h = new Room4TheCat(0, 1);
            Assert.fail("width/height zero not allowed");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            Room4TheCat h = new Room4TheCat(1, 0);
            Assert.fail("width/height zero not allowed");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

    }

    @Test(timeout = 50)
    public void customTest_room_constructor() {
        Room4TheCat r = new Room4TheCat(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Assert.assertEquals("width and height, Integer.MAX_VALUE", Integer.MAX_VALUE, r.getWidth());
        Assert.assertEquals("width and height, Integer.MAX_VALUE", Integer.MAX_VALUE, r.getHeight());

        try {
            r = new Room4TheCat(-1, 1);
            Assert.fail("no negative values allowed (no exception)");
        } catch (Exception t) {
            Assert.assertSame(IllegalArgumentException.class, t.getClass());
        }

        try {
            r = new Room4TheCat(1, -1);
            Assert.fail("no negative values allowed (no exception)");
        } catch (Exception t) {
            Assert.assertSame(IllegalArgumentException.class, t.getClass());
        }

        try {
            r = new Room4TheCat(Integer.MIN_VALUE, 1);
            Assert.fail("no negative values allowed (no exception)");
        } catch (Exception t) {
            Assert.assertSame(IllegalArgumentException.class, t.getClass());
        }

        try {
            r = new Room4TheCat(1, Integer.MIN_VALUE);
            Assert.fail("no negative values allowed (no exception)");
        } catch (Exception t) {
            Assert.assertSame(IllegalArgumentException.class, t.getClass());
        }

    }

    @Test(timeout = 50)
    public void customTest_home_constructor() {
        House4TheCat h = new House4TheCat((DYNAMIC_ALLOCATION ? Integer.MAX_VALUE : 1024), (DYNAMIC_ALLOCATION ? Integer.MAX_VALUE : 1024));
        Assert.assertEquals("width and height, Integer.MAX_VALUE", (DYNAMIC_ALLOCATION ? Integer.MAX_VALUE : 1024), h.getWidth());
        Assert.assertEquals("width and height, Integer.MAX_VALUE", (DYNAMIC_ALLOCATION ? Integer.MAX_VALUE : 1024), h.getHeight());

        try {
            h = new House4TheCat(-1, 1);
            Assert.fail("no negative values allowed (no exception)");
        } catch (Exception t) {
            Assert.assertSame(IllegalArgumentException.class, t.getClass());
        }

        try {
            h = new House4TheCat(1, -1);
            Assert.fail("no negative values allowed (no exception)");
        } catch (Exception t) {
            Assert.assertSame(IllegalArgumentException.class, t.getClass());
        }

        try {
            h = new House4TheCat(Integer.MIN_VALUE, 1);
            Assert.fail("no negative values allowed (no exception)");
        } catch (Exception t) {
            Assert.assertSame(IllegalArgumentException.class, t.getClass());
        }

        try {
            h = new House4TheCat(1, Integer.MIN_VALUE);
            Assert.fail("no negative values allowed (no exception)");
        } catch (Exception t) {
            Assert.assertSame(IllegalArgumentException.class, t.getClass());
        }

    }

    @Test(timeout = 300)
    public void customTest_home_room_constructor_random() {
        Room4TheCat r;
        House4TheCat h;
        for (int i = 0; i < (DYNAMIC_ALLOCATION ? 8192 : 1024); i++) {

            int rndW = randomInt(0, (DYNAMIC_ALLOCATION ? Integer.MAX_VALUE : 1024));
            int rndH = randomInt(0, (DYNAMIC_ALLOCATION ? Integer.MAX_VALUE : 1024));

            if (rndW == 0 || rndH == 0) {
                try {
                    r = new Room4TheCat(rndH, rndW);
                    h = new House4TheCat(rndH, rndW);
                    Assert.fail("room width/height zero not allowed");
                } catch (Exception e) {
                    Assert.assertSame(IllegalArgumentException.class, e.getClass());
                }
            } else {

                r = new Room4TheCat(rndH, rndW);
                h = new House4TheCat(rndH, rndW);

                Assert.assertEquals("constructor Room: " + rndW + " and " + rndH, rndW, r.getWidth());
                Assert.assertEquals("constructor Room: " + rndW + " and " + rndH, rndH, r.getHeight());

                Assert.assertEquals("constructor House: " + rndW + " and " + rndH, rndW, h.getWidth());
                Assert.assertEquals("constructor House: " + rndW + " and " + rndH, rndH, h.getHeight());
            }

        }
    }

    @Test(timeout = 50)
    public void customTest_room_getX_getY_setXY() {
        Room4TheCat r = new Room4TheCat(1, 1);
        Assert.assertEquals("Default values", -1, r.getX());
        Assert.assertEquals("Default values", -1, r.getY());

        r.setXY(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Assert.assertEquals("setAndGet: Integer.MAX_VALUE", Integer.MAX_VALUE, r.getX());
        Assert.assertEquals("setAndGet: Integer.MAX_VALUE", Integer.MAX_VALUE, r.getY());

        try {
            r.setXY(Integer.MIN_VALUE, Integer.MIN_VALUE);
            Assert.fail("Exception expected (values < -1 not allowed)");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }


        try {
            r.setXY(1, -1);
            Assert.fail("Exception expected (only one value negative not allowed)");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            r.setXY(-1, 1);
            Assert.fail("Exception expected (only one value negative not allowed)");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            r.setXY(0, -1);
            Assert.fail("Exception expected (only one value negative not allowed)");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            r.setXY(-1, 0);
            Assert.fail("Exception expected (only one value negative not allowed)");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            r.setXY(-1, -1);
            Assert.assertEquals(-1, r.getX());
            Assert.assertEquals(-1, r.getY());
        } catch (Exception e) {
            Assert.fail("This shouldn't cause an exception");
        }

        try {
            r.setXY(0, 0);
            Assert.assertEquals(0, r.getX());
            Assert.assertEquals(0, r.getY());
        } catch (Exception e) {
            Assert.fail("This shouldn't cause an exception");
        }


    }

    @Test(timeout = 500)
    public void customTest_room_getX_getY_setXY_random() {
        Room4TheCat r = new Room4TheCat(1, 1);

        for (int i = 0; i < 32768; i++) {

            int rndX = RND.nextInt(Integer.MAX_VALUE);
            int rndY = RND.nextInt(Integer.MAX_VALUE);

            r.setXY(rndX, rndY);

            Assert.assertEquals("setAndGet: " + rndX + " and " + rndY, rndX, r.getX());
            Assert.assertEquals("setAndGet: " + rndX + " and " + rndY, rndY, r.getY());
        }



        /*for (int i = 0; i < 32768; i++) {
            int rndX = -RND.nextInt(Integer.MAX_VALUE);
            int rndY = -RND.nextInt(Integer.MAX_VALUE);

            r.setXY(rndX, rndY);
            if (rndX == -1 || rndY == -1) {
                continue;
                // This assumes that you should return -1 if the room is not placed, this would be theoretically the case if only one of x y is -1
                / Unsure whether this is the right solution
                Assert.assertEquals("setAndGet: " + rndX + " and " + rndY, -1, r.getX());
                Assert.assertEquals("setAndGet: " + rndX + " and " + rndY, -1, r.getY());
            } else {
                Assert.assertEquals("setAndGet: " + rndX + " and " + rndY, rndX, r.getX());
                Assert.assertEquals("setAndGet: " + rndX + " and " + rndY, rndY, r.getY());
            }

        }*/

    }

    @Test(timeout = 500)
    public void customTest_home_canPlace() {
        House4TheCat h = new House4TheCat(50, 50);

        Room4TheCat r = new Room4TheCat(10, 10);

        for (int i = 0; i < 40; i++) {
            Assert.assertTrue("Moving towards x+", h.canPlace(r, i, 0));
            Assert.assertTrue("Moving towards y+", h.canPlace(r, 0, i));
        }

        for (int i = 1; i < 11; i++) {
            try {
                h.canPlace(r, 40 + i, 0);
                Assert.fail("should have thrown an exception - x too big");
            } catch (Exception t) {
                Assert.assertSame("x too big", IllegalArgumentException.class, t.getClass());
            }

            try {
                h.canPlace(r, 0, 40 + i);
                Assert.fail("should have thrown an exception - y too big");
            } catch (Exception t) {
                Assert.assertSame("y too big", IllegalArgumentException.class, t.getClass());
            }
        }

        try {
            h.canPlace(null, 0, 0);
            Assert.fail("should have thrown an exception - r is null");
        } catch (Exception t) {
            Assert.assertSame("r is null", IllegalArgumentException.class, t.getClass());
        }

        try {
            h.canPlace(r, -1, 0);
            Assert.fail("should have thrown an exception - x is < 0");
        } catch (Exception t) {
            Assert.assertSame("x is < 0", IllegalArgumentException.class, t.getClass());
        }

        try {
            h.canPlace(r, 0, -1);
            Assert.fail("should have thrown an exception - y is < 0");
        } catch (Exception t) {
            Assert.assertSame("y is < 0", IllegalArgumentException.class, t.getClass());
        }

        try {
            Room4TheCat empty = new Room4TheCat(1, 1);
            h.canPlace(empty, 0, 0);
        } catch (Exception t) {
            Assert.fail("should not have thrown an exception");
        }

        r = new Room4TheCat(10, 10);

        for (int i = 0; i < 8192; i++) {

            h = new House4TheCat(RND.nextInt(128) + 1, RND.nextInt(128) + 1);

            int mX = randomInt(h.getWidth(), Integer.MAX_VALUE);
            int mY = randomInt(h.getHeight(), Integer.MAX_VALUE);

            // (no maths just stupid)
            if (i % 2 == 0)
                mX = -mX;

            if (i % 3 == 0)
                mY = -mY;

            if (i % 4 == 0)
                mY = -mY;

            try {
                h.canPlace(r, mX, 0);
                Assert.fail("should have thrown an exception - mX: " + mX);
            } catch (Exception t) {
                Assert.assertSame("mX: " + mX, IllegalArgumentException.class, t.getClass());
            }

            try {
                h.canPlace(r, 0, mY);
                Assert.fail("should have thrown an exception - mY: " + mY);
            } catch (Exception t) {
                Assert.assertSame("mY: " + mY, IllegalArgumentException.class, t.getClass());
            }

            try {
                h.canPlace(r, mX, mY);
                Assert.fail("should have thrown an exception - mX: " + mX + " mY: " + mY);
            } catch (Exception t) {
                Assert.assertSame("mX: " + mX + " mY: " + mY, IllegalArgumentException.class, t.getClass());
            }

        }

    }

    @Test(timeout = 15000)
    public void customTest_home_place_getRoomAt() {
        House4TheCat house = new House4TheCat(128, 128);

        Room4TheCat r = new Room4TheCat(1, 1);

        // == getRoomAt ==

        for (int i = 0; i < house.getHeight(); i++) {
            for (int k = 0; k < house.getWidth(); k++) {
                Assert.assertNull("Empty house should not have rooms", house.getRoomAt(i, k));
            }
        }

        try {
            house.getRoomAt(-1, 0);
            Assert.fail("should have thrown an exception - (-1;0)");
        } catch (Exception t) {
            Assert.assertSame("(-1;0)", IllegalArgumentException.class, t.getClass());
        }

        try {
            house.getRoomAt(0, -1);
            Assert.fail("should have thrown an exception - (0;-1)");
        } catch (Exception t) {
            Assert.assertSame("(0;-1)", IllegalArgumentException.class, t.getClass());
        }

        try {
            house.getRoomAt(128, 0);
            Assert.fail("should have thrown an exception - (128;0)");
        } catch (Exception t) {
            Assert.assertSame("(128;0)", IllegalArgumentException.class, t.getClass());
        }

        try {
            house.getRoomAt(0, 128);
            Assert.fail("should have thrown an exception - (0;128)");
        } catch (Exception t) {
            Assert.assertSame("(0;128)", IllegalArgumentException.class, t.getClass());
        }

        try {
            house.getRoomAt(127, 127);
        } catch (Exception t) {
            Assert.fail("This should not lead to an exception");
        }

        // == getRoomAt fin ==

        // == place ==

        for (int i = 0; i < house.getHeight(); i++) {
            for (int k = 0; k < house.getWidth(); k++) {
                house.place(r, i, k);
                Assert.assertSame("rooms should be the same at (" + i + "," + k + ")", r, house.getRoomAt(i, k));
            }
        }

        r = new Room4TheCat(10, 10);

        try {
            house.place(null, 0, 0);
            Assert.fail("should have thrown an exception - r must not be null");
        } catch (Exception t) {
            Assert.assertSame("r must not be null", IllegalArgumentException.class, t.getClass());
        }

        try {
            house.place(r, -1, 0);
            Assert.fail("should have thrown an exception - x must not be less than 0");
        } catch (Exception t) {
            Assert.assertSame("x must not be less than 0", IllegalArgumentException.class, t.getClass());
        }

        try {
            house.place(r, 0, -1);
            Assert.fail("should have thrown an exception - y must not be less than 0");
        } catch (Exception t) {
            Assert.assertSame("y must not be less than 0", IllegalArgumentException.class, t.getClass());
        }

        try {
            house.place(r, 119, 0);
            Assert.fail("should have thrown an exception - x + room width must not be greater than the house width");
        } catch (Exception t) {
            Assert.assertSame("x + room width must not be greater than the house width", IllegalArgumentException.class, t.getClass());
        }

        try {
            house.place(r, 0, 119);
            Assert.fail("should have thrown an exception - y + room height must not be greater than the house height");
        } catch (Exception t) {
            Assert.assertSame("y + room height must not be greater than the house height", IllegalArgumentException.class, t.getClass());
        }

        house = new House4TheCat(128, 128);
        r = new Room4TheCat(1, 5);

        for (int i = 0; i < house.getHeight(); i++) {
            for (int k = 0; k < house.getWidth() - 5; k++) {
                house.place(r, k, i);
                Assert.assertSame("rooms should be the same at (" + i + "," + k + ")", r, house.getRoomAt(k, i));
            }
        }

        house = new House4TheCat(128, 128);
        r = new Room4TheCat(5, 1);

        for (int i = 0; i < house.getHeight() - 5; i++) {
            for (int k = 0; k < house.getWidth(); k++) {
                house.place(r, k, i);
                Assert.assertSame("rooms should be the same at (" + i + "," + k + ")", r, house.getRoomAt(k, i));
            }
        }

        // == place fin ==
    }

    @Test(timeout = 2000)
    public void customTest_home_place_random() {
        for (int i = 0; i < 384; i++) {
            House4TheCat threadHouse = new House4TheCat(randomInt(2, 512), randomInt(2, 512));
            for (int k = 0; k < 64; k++) {
                Room4TheCat threadRoom = new Room4TheCat(randomInt(1, (threadHouse.getHeight() / 2 + 1)), randomInt(1, (threadHouse.getWidth() / 2) + 1));

                int x = RND.nextInt(threadHouse.getWidth() - threadRoom.getWidth());
                int y = RND.nextInt(threadHouse.getHeight() - threadRoom.getHeight());

                if (threadHouse.canPlace(threadRoom, x, y)) {
                    threadHouse.place(threadRoom, x, y);
                    Assert.assertEquals(x, threadRoom.getX());
                    Assert.assertEquals(y, threadRoom.getY());
                    Assert.assertSame(threadRoom, threadHouse.getRoomAt(x, y));
                } else {
                    Assert.assertNotEquals(threadRoom, threadHouse.getRoomAt(x, y));
                }
            }
        }
    }

    @Test(timeout = 500)
    public void customTest_home_remove() {
        House4TheCat house = new House4TheCat(64, 64);

        Room4TheCat room = new Room4TheCat(1, 1);

        try {
            house.remove(null);
            Assert.fail("should have thrown an exception - r is null");
        } catch (Exception t) {
            Assert.assertSame(IllegalArgumentException.class, t.getClass());
        }

        try {
            house.remove(room);
            Assert.fail("should have thrown an exception - r is not in the house");
        } catch (Exception t) {
            Assert.assertSame(IllegalArgumentException.class, t.getClass());
        }

        for (int i = 0; i < house.getHeight(); i++) {
            for (int k = 0; k < house.getWidth(); k++) {
                Assert.assertNull(house.getRoomAt(i, k));
                house.place(room, i, k);
                Assert.assertSame(room, house.getRoomAt(i, k));
                house.remove(room);
                Assert.assertNull(house.getRoomAt(i, k));
            }
        }
    }

    @Test(timeout = 10000)
    public void customTest_home_remove_random() {
        for (int i = 0; i < 1024; i++) {

            House4TheCat threadHouse = new House4TheCat(randomInt(1, 512) + 2, randomInt(1, 512) + 2);

            ArrayList<Room4TheCat> placed = new ArrayList<>();

            for (int k = 0; k < 96; k++) {
                Room4TheCat threadRoom = new Room4TheCat(randomInt(1, (threadHouse.getHeight() / 4) + 2), randomInt(1, (threadHouse.getWidth() / 4) + 2));

                int x = RND.nextInt(threadHouse.getWidth() - threadRoom.getWidth());
                int y = RND.nextInt(threadHouse.getHeight() - threadRoom.getHeight());

                if (threadHouse.canPlace(threadRoom, x, y)) {
                    threadHouse.place(threadRoom, x, y);
                    Assert.assertEquals(x, threadRoom.getX());
                    Assert.assertEquals(y, threadRoom.getY());
                    Assert.assertSame(threadRoom, threadHouse.getRoomAt(x, y));
                    placed.add(threadRoom);
                } else {
                    Assert.assertNotEquals(threadRoom, threadHouse.getRoomAt(x, y));
                }
            }

            getSi(threadHouse, placed);
        }
    }

    @Test(timeout = 1000)
    public void customTest_home_placeNext() {
        House4TheCat house = new House4TheCat(8, 8);
        Room4TheCat room = new Room4TheCat(2, 4);

        try {
            room.placeNext(null);
            Assert.fail("should have thrown an exception - House must not be null");
        } catch (Exception t) {
            Assert.assertSame("House must not be null", IllegalArgumentException.class, t.getClass());
        }

        for (int i = 0; i < house.getHeight() - room.getHeight() + 1; i++) {
            for (int k = 0; k < house.getWidth() - room.getWidth() + 1; k++) {
                boolean res = room.placeNext(house);
                Assert.assertTrue("Room should be placed", res);
                for (int x = 0; x < 4; x++) {
                    Assert.assertEquals("There should be a room here (" + (k + x) + "," + i + ")", room, house.getRoomAt(k + x, i));
                }
            }
        }

        house = new House4TheCat(128, 128);
        room = new Room4TheCat(4, 2);
        for (int i = 0; i < house.getHeight() - room.getHeight() + 1; i++) {
            for (int k = 0; k < house.getWidth() - room.getWidth() + 1; k++) {
                boolean res = room.placeNext(house);
                Assert.assertTrue("Room should be placed", res);
                for (int x = 0; x < 4; x++) {
                    Assert.assertEquals("There should be a room here (" + (k) + "," + (i + x) + ")", room, house.getRoomAt(k, i + x));
                }
            }
        }

        house = new House4TheCat(64, 256);
        room = new Room4TheCat(16, 2);
        for (int i = 0; i < house.getHeight() - room.getHeight() + 1; i++) {
            for (int k = 0; k < house.getWidth() - room.getWidth() + 1; k++) {
                boolean res = room.placeNext(house);
                Assert.assertTrue("Room should be placed", res);
                for (int x = 0; x < 16; x++) {
                    Assert.assertEquals("There should be a room here (" + (k) + "," + (i + x) + ")", room, house.getRoomAt(k, i + x));
                }
            }
        }

        house = new House4TheCat(128, 128);
        room = new Room4TheCat(3, 3);

        for (int i = 0; i < house.getHeight() - room.getHeight() + 1; i++) {
            for (int k = 0; k < house.getWidth() - room.getWidth() + 1; k++) {
                boolean res = room.placeNext(house);
                Assert.assertTrue("Room should be placed", res);
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        Assert.assertEquals("There should be a room here (" + (k + x) + "," + (i + y) + ")", room, house.getRoomAt(k + x, i + y));
                    }
                }
            }
        }


    }

    @Test(timeout = 10000)
    public void customTest_home_placeNext_random() {
        for (int i = 0; i < 6; i++) {

            House4TheCat threadHouse = new House4TheCat(randomInt(1, 512) + 2, randomInt(1, 512) + 2);

            ArrayList<Room4TheCat> placed = new ArrayList<>();

            for (int k = 0; k < 96; k++) {
                Room4TheCat threadRoom = new Room4TheCat(randomInt(1, (threadHouse.getHeight() / 4) + 2), randomInt(1, (threadHouse.getWidth() / 4) + 2));

                if (threadRoom.placeNext(threadHouse)) {
                    placed.add(threadRoom);
                }
            }

            getSi(threadHouse, placed);
        }
    }

    private void getSi(House4TheCat threadHouse, ArrayList<Room4TheCat> placed) {
        int si = placed.size();
        for (Room4TheCat currentRoom : placed) {
            int prevX = currentRoom.getX();
            int prevY = currentRoom.getY();
            threadHouse.remove(currentRoom);
            if (threadHouse.getRoomAt(prevX, prevY) == null)
                si--;
        }

        Assert.assertEquals("Couldn't remove all placed rooms", 0, si);
    }

    @Test(timeout = 500)
    public void customTest_home_placeAll() {
        // For a better random test, just adjust the values in the public test
        // for example increasing max house size, other than that there really isn't that much to do
        House4TheCat house = new House4TheCat(16, 16);
        Room4TheCat r = new Room4TheCat(1, 16);

        try {
            house.placeAll(null);
            Assert.fail("should have thrown an exception - rs is null");
        } catch (Exception t) {
            Assert.assertSame("Wrong exception type", IllegalArgumentException.class, t.getClass());
        }

        try {
            house.placeAll(new Room4TheCat[]{null});
            Assert.fail("should have thrown an exception - rs contains null");
        } catch (Exception t) {
            Assert.assertSame("Wrong exception type", IllegalArgumentException.class, t.getClass());
        }

        Assert.assertTrue("One big room", house.placeAll(new Room4TheCat[]{new Room4TheCat(16, 16)}));
        house = new House4TheCat(16, 16);

        Room4TheCat[] yikes = new Room4TheCat[]{r, clone(r), clone(r), clone(r), clone(r), clone(r), clone(r), clone(r), clone(r), clone(r), clone(r), clone(r), clone(r), clone(r), clone(r), clone(r)};

        Assert.assertTrue(house.placeAll(yikes));

        for (int i = 0; i < 16; i++) {
            Assert.assertEquals(yikes[i], house.getRoomAt(i, i));
        }

    }

    // -- non test parts --

    private Room4TheCat clone(Room4TheCat c) {
        return new Room4TheCat(c.getHeight(), c.getWidth());
    }

    private int randomInt(int source, int bound) {
        return ThreadLocalRandom.current().nextInt(source, bound);
    }

    // for debug purposes
    private void printOutHouse(House4TheCat hc) {
        for (int i = 0; i < hc.getHeight(); i++) {
            for (int j = 0; j < hc.getWidth(); j++) {
                Room4TheCat r = hc.getRoomAt(j, i);
                if (r != null)
                    System.out.print(r.toString().substring(11) + ",");
                else
                    System.out.print("null,");

            }
            System.out.println();
        }
    }
}
