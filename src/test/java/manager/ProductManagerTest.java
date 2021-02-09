package manager;

import domain.Book;
import domain.Product;
import domain.ProductRepository;
import domain.Smartphone;
import exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductManagerTest {
    @InjectMocks
    private ProductManager manager;

    @Mock
    private ProductRepository repository;

    Product anotherProduct;
    Book book1;
    Book book2;
    Book book3;
    Smartphone smartphone1;
    Smartphone smartphone2;
    Smartphone smartphone3;

    @BeforeEach
    private void setUp() {

        anotherProduct = new Product(0, "продукт", 0);
        book1 = new Book(1, "Война и мир", 100, "Толстой");
        book2 = new Book(2, "Бесприданница", 1001, "Островский");
        book3 = new Book(5, "Анна Каренина", 2500, "Толстой");
        smartphone1 = new Smartphone(3, "Айфон", 1002, "Apple");
        smartphone2 = new Smartphone(4, "m1", 1003, "Sony");
        smartphone3 = new Smartphone(6, "qwerty", 1001, "Бесприданница");
    }

    private Product[] getAllDumb() {
        return new Product[]{anotherProduct, book1, book2, smartphone1, smartphone2, book3, smartphone3};
    }

    @Test
    void searchByNameBook() {
        Product[] returned = getAllDumb();
        doReturn(returned).when(repository).getAll();
        Product[] actual = manager.searchBy("Война и мир");
        assertArrayEquals(new Product[]{book1}, actual);
        verify(repository).getAll();
    }

    @Test
    void searchByAuthorBook() {
        Product[] returned = getAllDumb();
        doReturn(returned).when(repository).getAll();
        Product[] actual = manager.searchBy("Островский");
        assertArrayEquals(new Product[]{book2}, actual);
        verify(repository).getAll();
    }


    @Test
    void searchByNameSmartphone() {
        Product[] returned = getAllDumb();
        doReturn(returned).when(repository).getAll();
        Product[] actual = manager.searchBy("Айфон");
        assertArrayEquals(new Product[]{smartphone1}, actual);
        verify(repository).getAll();
    }


    @Test
    void searchByManufactureSmartphone() {
        Product[] returned = getAllDumb();
        doReturn(returned).when(repository).getAll();
        Product[] actual = manager.searchBy("Sony");
        assertArrayEquals(new Product[]{smartphone2}, actual);
        verify(repository).getAll();
    }

    @Test
    void searchByNothing() {
        Product[] returned = getAllDumb();
        doReturn(returned).when(repository).getAll();
        Product[] actual = manager.searchBy("Достоевский");
        assertArrayEquals(new Product[]{}, actual);
        verify(repository).getAll();
    }

    @Test
    void searchByMoreOne() {
        Product[] returned = getAllDumb();
        doReturn(returned).when(repository).getAll();
        Product[] actual = manager.searchBy("Толстой");
        assertArrayEquals(new Product[]{book1, book3}, actual);
        verify(repository).getAll();
    }

    @Test
    void searchByMoreOneDifferentProperties() {
        Product[] returned = getAllDumb();
        doReturn(returned).when(repository).getAll();
        Product[] actual = manager.searchBy("Бесприданница");
        assertArrayEquals(new Product[]{book2, smartphone3}, actual);
        verify(repository).getAll();
    }


    @Test
    void removeById() {
        ProductRepository r = new ProductRepository();
        r.save(book1);
        r.save(book2);
        r.removeById(book1.getId());
        Product[] actual = r.getAll();
        assertArrayEquals(new Product[]{book2}, actual);

    }

    @Test
    void removeByIdException() {
        ProductRepository r = new ProductRepository();
        r.save(book1);
        r.save(book2);
        assertThrows(NotFoundException.class, ()->r.removeById(25));


    }
}