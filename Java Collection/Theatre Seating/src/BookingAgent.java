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
    }
