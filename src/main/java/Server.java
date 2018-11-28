import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class Server {

    private static List<Player> players = new ArrayList<>();

    public static void main(String[] args) {

        Javalin app = Javalin.create()
                .enableStaticFiles("/public")
                .start(7770);

        app.post("/", ctx -> {
            String nickname = ctx.formParam("nickname");
            players.add(new Player(nickname));
            ctx.sessionAttribute("nickname", nickname);
            ctx.redirect("/create-gotchi");
        });

        app.get("/", ctx -> {
            ctx.render("public/login.twig");
        });

        app.get("/create-gotchi", ctx -> {
            ctx.render("public/create-gotchi.html.twig", model("nickname", ctx.sessionAttribute("nickname")));
        });
    }

}

