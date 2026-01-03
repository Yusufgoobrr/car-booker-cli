package com.yusuf.user;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserFileDataAccsessServiceTest {

    @TempDir
    Path tempDir;

    private UserFileDataAccsessService userFileDataAccsessService;

    @BeforeEach
    void setUp() throws Exception {
        Path usersFile = tempDir.resolve("users.txt");

        Files.writeString(
                usersFile,
                """
                11111111-1111-1111-1111-111111111111,Yusuf,Kaya
                22222222-2222-2222-2222-222222222222,Ali,Demir
                33333333-3333-3333-3333-333333333333,Ayse,Yilmaz
                44444444-4444-4444-4444-444444444444,Mehmet,Arslan
                55555555-5555-5555-5555-555555555555,Zeynep,Celik
                66666666-6666-6666-6666-666666666666,Elif,Aydin
                77777777-7777-7777-7777-777777777777,Can,Ozturk
                88888888-8888-8888-8888-888888888888,Emre,Sahin
                99999999-9999-9999-9999-999999999999,Buse,Koc
                aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa,Mert,Aksoy
                """
        );

        userFileDataAccsessService =
                new UserFileDataAccsessService(usersFile.toString());
    }

    @Test
    void getAllUsers() {
        List<User> expectedUsers = List.of(
                new User(UUID.fromString("11111111-1111-1111-1111-111111111111"), "Yusuf", "Kaya"),
                new User(UUID.fromString("22222222-2222-2222-2222-222222222222"), "Ali", "Demir"),
                new User(UUID.fromString("33333333-3333-3333-3333-333333333333"), "Ayse", "Yilmaz"),
                new User(UUID.fromString("44444444-4444-4444-4444-444444444444"), "Mehmet", "Arslan"),
                new User(UUID.fromString("55555555-5555-5555-5555-555555555555"), "Zeynep", "Celik"),
                new User(UUID.fromString("66666666-6666-6666-6666-666666666666"), "Elif", "Aydin"),
                new User(UUID.fromString("77777777-7777-7777-7777-777777777777"), "Can", "Ozturk"),
                new User(UUID.fromString("88888888-8888-8888-8888-888888888888"), "Emre", "Sahin"),
                new User(UUID.fromString("99999999-9999-9999-9999-999999999999"), "Buse", "Koc"),
                new User(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"), "Mert", "Aksoy")
        );

        List<User> users = userFileDataAccsessService.getAllUsers();

        assertThat(users)
                .hasSize(10)
                .containsExactlyInAnyOrderElementsOf(expectedUsers);
    }
}
