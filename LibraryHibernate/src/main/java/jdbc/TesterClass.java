package jdbc;
import java.util.List;

import appSpecs.DBServices;
import entities.Book;
import entities.Transaction;

public class TesterClass {

	public static void main(String[] args) {

		DBServices services = new DBServices();
		List<Book> books = services.selectAllBooks();
		Transaction trans = services.selectTransById(1);
		System.out.println(services.selectBookById(trans.getBookid()).getAuthor());
//		System.out.println(services.selectUserByName("esa").get(0).getIsAdmin());
//		System.out.println(services.selectUserById(1).get(0).getIsAdmin());
//		System.out.println(services.selectBookById(4).getAuthor());
//		for(int i=0;i<books.size();i++){
//	
//			System.out.println(books.get(i).getTitle());
//		}
		
	}

}
