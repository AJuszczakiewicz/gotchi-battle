import io.javalin.Javalin;
import java.util.HashMap;
import java.util.Map;

import io.javalin.rendering.JavalinRenderer;
import io.javalin.rendering.template.JavalinJtwig;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import io.javalin.Javalin;

import static io.javalin.rendering.template.TemplateUtil.model;

public class Server {

    public static void main(String[] args) {
        Javalin app = Javalin.create()
                .enableStaticFiles("/public")
                .start(7777);

        app.post("/create-gotchi", ctx -> {
            ctx.formParam("nickname");
            String nickname = ctx.formParam("nickname");
            nickname = String.format("Hello %s", nickname);
            ctx.html(nickname);
        });

        app.get("/create-gotchi", ctx -> {
        });

        app.get("/test", ctx -> {
           ctx.render("public/index1.jtwig", model("nickname", "Lex"));
        });
    }

}