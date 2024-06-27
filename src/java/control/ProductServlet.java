package control;

import dao.BookDAO;
import entity.Product.Author;
import entity.Product.Book;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@WebServlet(urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

    private static final int ITEMS_PER_PAGE = 15;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (null == action) {
            doGetDisplay(request, response);
        } else {
            switch (action) {
                case "newArrival":
                    doGetNewArrival(request, response);
                    break;
                case "show":
                    doGetDetail(request, response);
                    break;
                case "category":
                    doGetCatagory(request, response);
                    break;
                case "search":
//                    String data = request.getParameter("input");
//                    List<Book> books = BookDAO.searchBook(data);
//                    pagingPage(request, response, books);
                    doGetSearch(request, response);
                    break;
                case "comingsoon":
                    doGetBookGacXep(request, response);
                    break;
                case "asctitle":
                    doGetAscTitle(request, response);
                    break;
                case "desctitle":
                    doGetDescTitle(request, response);
                    break;
                case "ascdate":
                    doGetAscDate(request, response);
                    break;
                case "descdate":
                    doGetAscDate(request, response);
                    break;
                case "ascprice":
                    doGetAscPrice(request, response);
                    break;
                case "descprice":
                    doGetDescPrice(request, response);
                    break;
            }
        }
    }

    protected void doGetNewArrival(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = BookDAO.getNewBooks(15);
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newArrival.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGetDisplay(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        int indexPage = Integer.parseInt(page);
        int totalPage = BookDAO.getNumberPage();
        List<Author> authors = BookDAO.getAuthors();
        List<Book> books = BookDAO.getPagingBook(indexPage);
        request.setAttribute("books", books);
        request.setAttribute("authors", authors);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("indexPage", indexPage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/allBook.jsp");
        dispatcher.forward(request, response);
    }

    protected void pagingPage(HttpServletRequest request, HttpServletResponse response, List<Book> books) {
        List<Author> authors = BookDAO.getAuthors();
        int totalBooks = books.size();
        int totalPagesBook = (int) Math.ceil((double) totalBooks / ITEMS_PER_PAGE);
        String currentPageBookParam = request.getParameter("page");
        int currentPageBook = (currentPageBookParam != null) ? Integer.parseInt(currentPageBookParam) : 1;
        int offsetBook = (currentPageBook - 1) * ITEMS_PER_PAGE;
        List<Book> pagingBooks = books.subList(offsetBook, ITEMS_PER_PAGE);
        request.setAttribute("books", pagingBooks);
        request.setAttribute("authors", authors);
        request.setAttribute("totalPage", totalPagesBook);
        request.setAttribute("indexPage", currentPageBook);
    }

    protected void doGetDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        Book book = BookDAO.getBookById(Integer.parseInt(bookId));
        request.setAttribute("book", book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookDetail.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGetSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String data = request.getParameter("input");
        List<Book> books = BookDAO.searchBook(data);
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/searchPage.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGetCatagory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String data = request.getParameter("input");
        String category = request.getParameter("category");
        if (category.equals("AuthorId")) {
            List<Book> books = BookDAO.searchBook(category, data);
        }
        List<Book> books = BookDAO.searchBook(category, data);
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGetBookGacXep(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = new ArrayList<>();
        int n = 6;
        for (int i = 1; i <= n; i++) {
            Document doc = (Document) Jsoup.connect("https://gacxepbookstore.vn/all-books?q=collections:2113434&page=" + i + "&sortby=created_on:desc&view=grid").get();

            Elements elements = doc.getElementsByClass("product-loop-1 product-loop-2 product-loop-col product-base");
            // System.out.println(elements);
            for (Element e : elements) {

                String title = e.getElementsByClass("image_link display_flex").attr("title");
                String prices[] = e.getElementsByClass("price product-price").text().split(" ");
                String price = prices[0].replace(".", "");
                Elements imageUrlElements = e.select("a.image_link.display_flex");
                List<String> imageUrls = new ArrayList<>();
                for (Element e1 : imageUrlElements) {
                    String imageUrl = e1.getElementsByTag("img").attr("data-lazyload");
                    imageUrls.add(imageUrl);
                }

                Book newbook = new Book(2, title, Float.parseFloat(price), imageUrls.get(0));
                books.add(newbook);

            }
        }
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newArrival.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGetAscTitle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = BookDAO.SortBookAsc("title");
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newArrival.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGetDescTitle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = BookDAO.SortBookDesc("title");
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newArrival.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGetAscPrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = BookDAO.SortBookAsc("price");
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newArrival.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGetDescPrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = BookDAO.SortBookDesc("price");
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newArrival.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGetDescDate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = BookDAO.SortBookDescDate();
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newArrival.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGetAscDate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = BookDAO.SortBookAscDate();
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newArrival.jsp");
        dispatcher.forward(request, response);
    }

    public static void main(String[] args) {

        BookDAO.getAuthors().forEach(p -> System.out.println(p));
    }
}
