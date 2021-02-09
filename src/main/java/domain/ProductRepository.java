package domain;

import exceptions.NotFoundException;

public class ProductRepository {
    private Product[] items = new Product[0];

    public void save(Product item) {
        int length = items.length + 1;
        Product[] newProducts = new Product[length];
        for (int i = 0; i < items.length; i++) {
            newProducts[i] = items[i];
        }
        newProducts[items.length] = item;
        this.items = newProducts;
    }

    public Product[] getAll() {
        return items;
    }

    private Product findById(int id) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId() == id)
                return items[i];
        }

        return null;
    }

    public void removeById(int id) {

        Product find = findById(id);

        if (find != null) {

            int length = items.length - 1;
            Product[] newProducts = new Product[length];

            int j = 0;
            for (int i = 0; i < items.length; i++) {
                Product item = items[i];
                if (item.getId() != id) {
                    newProducts[j] = item;
                    j = j + 1;
                }
            }
            items = newProducts;
        } else {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
    }

}
