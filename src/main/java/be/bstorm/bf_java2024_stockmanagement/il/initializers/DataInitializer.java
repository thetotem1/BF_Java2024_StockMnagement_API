package be.bstorm.bf_java2024_stockmanagement.il.initializers;

import be.bstorm.bf_java2024_stockmanagement.dal.repositories.*;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.*;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Client;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Extern;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Supplier;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.StockMovementType;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final StockMovementRepository movementRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ExternRepository externRepository;

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                    new Category(UUID.randomUUID(), "Jeux vidéo"),
                    new Category(UUID.randomUUID(), "Livres"),
                    new Category(UUID.randomUUID(), "Films")
            );
            categoryRepository.saveAll(categories);
        }

        if (articleRepository.count() == 0) {
            List<Category> categories = categoryRepository.findAll();
            List<Article> articles = List.of(
                    new Article(
                            UUID.randomUUID(),
                            "Dragon ball sparkling zero",
                            4999L,
                            VAT.TWENTY_ONE,
                            "Dragonballsparklingzero.jpg",
                            categories.stream().filter(
                                    c -> c.getDesignation().equals("Jeux vidéo")).findFirst().orElseThrow()
                    ),
                    new Article(
                            UUID.randomUUID(),
                            "Sun Tzu, L'art de la guèrre",
                            599L,
                            VAT.TWENTY_ONE,
                            null,
                            categories.stream().filter(
                                    c -> c.getDesignation().equals("Livres")).findFirst().orElseThrow()
                    ),
                    new Article(
                            UUID.randomUUID(),
                            "Le dernier samurai",
                            399L,
                            VAT.TWENTY_ONE,
                            null,
                            categories.stream().filter(
                                    c -> c.getDesignation().equals("Films")).findFirst().orElseThrow()
                    )
            );
            articleRepository.saveAll(articles);
        }

        if (movementRepository.count() == 0) {

            List<Article> articles = articleRepository.findAll();

            List<StockMovement> movements = List.of(
                    new StockMovement(
                            UUID.randomUUID(),
                            StockMovementType.STOCK_IN,
                            10,
                            LocalDateTime.now(),
                            articles.stream().filter(
                                    a -> a.getDesignation().equals("Dragon ball sparkling zero")).findFirst().orElseThrow()
                    ),
                    new StockMovement(
                            UUID.randomUUID(),
                            StockMovementType.STOCK_IN,
                            20,
                            LocalDateTime.now(),
                            articles.stream().filter(
                                    a -> a.getDesignation().equals("Sun Tzu, L'art de la guèrre")).findFirst().orElseThrow()
                    ),
                    new StockMovement(
                            UUID.randomUUID(),
                            StockMovementType.STOCK_IN,
                            50,
                            LocalDateTime.now(),
                            articles.stream().filter(
                                    a -> a.getDesignation().equals("Le dernier samurai")).findFirst().orElseThrow()
                    )
            );

            for (StockMovement movement : movements) {
                movementRepository.insertStockMovement(movement.getArticle().getId(), movement.getMovementType().toString(), movement.getQuantity());
            }

            if (roleRepository.count() == 0) {
                List<Role> roles = List.of(
                        new Role(UUID.randomUUID(), "ADMIN"),
                        new Role(UUID.randomUUID(), "USER")
                );
                roleRepository.saveAll(roles);
            }

            if (userRepository.count() == 0) {
                String password = passwordEncoder.encode("Test1234=");
                User admin = new User(
                        UUID.randomUUID(),
                        "Admin",
                        "Admin",
                        "Admin@test.be",
                        password
                );
                admin.addRole(roleRepository.findByName("ADMIN").orElseThrow());
                User user = new User(
                        UUID.randomUUID(),
                        "User",
                        "User",
                        "User@test.be",
                        password
                );
                admin.addRole(roleRepository.findByName("USER").orElseThrow());

                userRepository.saveAll(List.of(admin, user));
            }

            if(externRepository.count() == 0) {
                List<Extern> externs = List.of(
                        new Client(
                                UUID.randomUUID(),
                                "Client",
                                "Client",
                                "Client@test.be",
                                "0498969598",
                                new Address(
                                        "Rue cerisier 18",
                                        "Truc",
                                        "Truc",
                                        "4569"
                                )
                        ),
                        new Supplier(
                                UUID.randomUUID(),
                                "Supplier",
                                "Supplier",
                                "Supplier@test.be",
                                "0495969598",
                                new Address(
                                        "Rue cerisier 18",
                                        "Truc",
                                        "Truc",
                                        "4569"
                                )
                        )
                );
                externRepository.saveAll(externs);
            }
        }
    }
}
