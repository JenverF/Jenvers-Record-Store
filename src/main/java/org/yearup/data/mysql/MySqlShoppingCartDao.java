package org.yearup.data.mysql;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart cart = new ShoppingCart();
        String query = """
                SELECT sc.product_id, sc.quantity, p.name, p.price, p.category_id, p.description,
                    p.subcategory, p.image_url, p.stock, p.featured
                FROM shopping_cart as sc
                INNER JOIN products as p
                    ON sc.product_id = p.product_id
                WHERE user_id = ?;""";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, userId);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = mapRow(resultSet);
                    ShoppingCartItem cartItem = new ShoppingCartItem();
                    cartItem.setProduct(product);
                    cartItem.setQuantity(resultSet.getInt("quantity"));

                    cart.add(cartItem);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    @Override
    public ShoppingCart addToCart(int userId, int productId) {
        return null;
    }

    @Override
    public void updateCart(int userId, int productId, int quantity) {

    }

    @Override
    public void clearCart(int userId) {

    }



    protected static Product mapRow(ResultSet row) throws SQLException
    {
        int productId = row.getInt("product_id");
        String name = row.getString("name");
        BigDecimal price = row.getBigDecimal("price");
        int categoryId = row.getInt("category_id");
        String description = row.getString("description");
        String subCategory = row.getString("subcategory");
        int stock = row.getInt("stock");
        boolean isFeatured = row.getBoolean("featured");
        String imageUrl = row.getString("image_url");

        return new Product(productId, name, price, categoryId, description, subCategory, stock, isFeatured, imageUrl);
    }
}
