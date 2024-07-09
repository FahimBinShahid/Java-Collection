public class BookingAgent {
    public static void main(String[] args) {

        int rows = 10;
        int totalSeats = 100;
        Theatre StarCineplexSony = new Theatre("SonySquire",
                rows, totalSeats);

        StarCineplexSony.printSeatMap();
        bookSeat(StarCineplexSony, 'A', 3);
        bookSeat(StarCineplexSony,'B',1);
        bookSeat(StarCineplexSony, 'C',6);

        bookSeat(StarCineplexSony, 'M',11);

        bookSeats(StarCineplexSony,4,'B',3,10);


        bookSeats(StarCineplexSony, 6, 'B', 'C',1,5);

        bookSeats(StarCineplexSony,4,'B',1,10);
        bookSeats(StarCineplexSony,3,'C',1,7);


    }

    private static void bookSeat(Theatre theatre, char row, int seatNo) {
        String seat = theatre.reserveSeat(row,seatNo);

            if(seat != null) {
                System.out.println(" 503 Congrats! your seat is " + seat);
                theatre.printSeatMap();
            }else{
                System.out.println("404 not found"+ row + seatNo);

            }
        }

        private static void bookSeats(Theatre theatre, int tickets , char minRow,
                                      int minSeat, int maxSeat) {
        bookSeats(theatre, tickets, minRow, minRow,minSeat,maxSeat);
        }

        private static void bookSeats(Theatre theatre , int tickets, char minRow, char maxRow,
                                      int minSeat, int maxSeat) {
        var seats = theatre.reserveSeats(tickets,minRow,maxRow,minSeat,maxSeat);

        if(seats != null) {
            System.out.println("Congrats! your Seats are " + seats);
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry! No matching seats available : " + minRow + " - " + maxRow);
        }

        }
    }
