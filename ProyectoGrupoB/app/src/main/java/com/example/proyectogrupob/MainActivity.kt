package com.example.proyectogrupob

import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.proyectogrupob.Model.Conferencia
import com.example.proyectogrupob.Model.Ponentes
import com.example.proyectogrupob.UI.Fragments.CalendarioFragment
import com.example.proyectogrupob.UI.Fragments.ExpositoresFragment
import com.example.proyectogrupob.UI.Fragments.HomeFragment
import com.example.proyectogrupob.UI.Fragments.UbicacionFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var navigation: BottomNavigationView

    private val mOnNavMenu = BottomNavigationView.OnNavigationItemSelectedListener {
        item->

        when(item.itemId){
            R.id.NAVHomeFragment -> {
                supportFragmentManager.commit {
                    replace<HomeFragment>(R.id.relativeLayout)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.NAVProgramaFragment -> {
                supportFragmentManager.commit {
                    replace<CalendarioFragment>(R.id.relativeLayout)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.NAVMapaFragment -> {
                supportFragmentManager.commit {
                    replace<UbicacionFragment>(R.id.relativeLayout)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.NAVExpositoresFragment -> {
                supportFragmentManager.commit {
                    replace<ExpositoresFragment>(R.id.relativeLayout)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

    }
    false
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation = findViewById(R.id.BNVMenu)
        navigation.setOnNavigationItemSelectedListener(mOnNavMenu)

        supportFragmentManager.commit {
            replace<HomeFragment>(R.id.relativeLayout)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        /*val jsonArr = JSONArray("[\n" +
                "            {\n" +
                "                'biografia' : 'Pedro Juarez, creativo y content creator del equipo de Indrive, transforma las ideas en historias inspiradoras y contenidos innovadores que impactan al mundo para generar gracias, no solo likes, de nada sirve tu talento si no estas haciendo algo con el, de nada sirven tus habilidades si no tienes ideas para fusionarlas.',\n" +
                "                'categoria' : 5,\n" +
                "                'imagen' : 'https://pbs.twimg.com/profile_imagens/1015273976438902784/T0gZSbTP_400x400.jpg',\n" +
                "                'perfil' : 'Programador Junior',\n" +
                "                'nombre' : 'Pedro Juarez',\n" +
                "                'instagram' : '@juarezpedro',\n" +
                "                'trabajo' : 'InDrive'\n" +
                "            },\n" +
                "            {\n" +
                "                'biografia' : 'Soy Jorge. Alto, flaco y ojeroso. Hago footing los domingos. Head of production at @tester',\n" +
                "                'categoria' : 12,\n" +
                "                'imagen' : 'https://pbs.twimg.com/profile_imagens/902939605221199872/eJypBkO__400x400.jpg',\n" +
                "                'perfil' : 'Head of Production',\n" +
                "                'nombre' : 'Jorge Vergara',\n" +
                "                'instagram' : '@jorgitos',\n" +
                "                'trabajo' : 'Tester Prime'\n" +
                "            },\n" +
                "            {\n" +
                "                'biografia' : 'CEO y Fundador de Capterra la web de las marcas y que además que fue reconocido por la revista Forbes México como promesa de negocios del 2018.',\n" +
                "                'categoria' : 10,\n" +
                "                'imagen' : 'https://pbs.twimg.com/profile_imagens/1042801434989879296/Dya62rEB_400x400.jpg',\n" +
                "                'perfil' : 'Founder & CEO',\n" +
                "                'nombre' : 'Manuel Barreto',\n" +
                "                'instagram' : '@manuelbarreroco',\n" +
                "                'trabajo' : 'Capterra'\n" +
                "            },\n" +
                "            {\n" +
                "                'biografia' : 'Líder del equipo de Growth en Outsiden y Google Doc Expert en Marketing, especialista en análisis de datos, presupuesto y proyecciones de Startups, lo que lo ha llevado a ser un gran conferencista a nivel mundial. Recuerden si estamos haciendo algo y no estamos aprendiendo de ello, no sirve de nada.',\n" +
                "                'categoria' : 9,\n" +
                "                'imagen' : 'https://pbs.twimg.com/profile_imagens/1128429578647736321/5ZwuI5_Q_400x400.jpg',\n" +
                "                'perfil' : 'Head of Growth',\n" +
                "                'nombre' : 'Edwin Tenorio',\n" +
                "                'instagram' : '@tenorifico',\n" +
                "                'trabajo' : 'Outsiden'\n" +
                "            },\n" +
                "            {\n" +
                "                'biografia' : 'Ricardo Llerena es Google Developer Expert, Co-fundador y Chief Technology en Metix Medical. Es un gran facilitador de equipos con metodologías ágiles y reconocido profesor de Google. SRE en Globant. Google Developer Expert y Google Cloud Authorized Trainer. Organizador en GDG Cloud MX.',\n" +
                "                'categoria' : 6,\n" +
                "                'imagen' : 'https://pbs.twimg.com/profile_imagens/1225834733549965312/JixgS3m__400x400.jpg',\n" +
                "                'perfil' : 'Co-founder & CTO',\n" +
                "                'nombre' : 'Ricardo Llerena',\n" +
                "                'instagram' : '@ricardoelbueno',\n" +
                "                'trabajo' : 'Metrix'\n" +
                "            },\n" +
                "            {\n" +
                "                'biografia' : 'Mario Valle es uno de los mexicanos más prominentes de Silicon Valley. Fue pionero de la industria de videojuegos en América Latina y por 11 años fue ejecutivo de la gigante Electronic Arts.\u2028¿Por qué, entonces, dejarlo todo para lanzarse a ser inversionista?\u2028Mario (donante y fan #terco de la primera hora) nos cuenta que estaba cómodo y contento en EA. Pero la oportunidad que vio de invertir en videojuegos de creadores independientes en Asia, Europa, América Latina y hasta África le pareció demasiado grande para dejarla pasar.\u2028Lanzó Altered Ventures, un fondo que invierte como si fuera una productora de Hollywood: Le mete dinero a los proyectos, los videojuegos, y no a las empresas. Por lo tanto, no les pide a los desarrolladores que le cedan un porcentaje de propiedad de sus compañías, lo que –dice– en regiones como América Latina lleva a abusos de parte de los inversionistas. Mario tiene 36 millones de dólares para invertir en nuevos títulos y parte de su perfil para lanzar el fondo fue viajar por el mundo jugando videojuegos para conocer qué es lo que viene. Ya invirtió en un juego innovador hecho en Noruega (que se lanza el 21 de junio en Nintendo Switch) y pronto vienen más.',\n" +
                "                'categoria' : 3,\n" +
                "                'imagen' : 'https://pbs.twimg.com/profile_imagens/1177427481831194625/MGmAHgel_400x400.jpg',\n" +
                "                'perfil' : 'Angel Investor & Co-founder',\n" +
                "                'nombre' : 'Mario Valle',\n" +
                "                'instagram' : '@bilbeny',\n" +
                "                'trabajo' : 'Altered Ventures'\n" +
                "            },\n" +
                "            {\n" +
                "                'biografia' : 'Tuerto en país de ciegos | Nerd empedernido | Constructor de marcas | Estratega digital',\n" +
                "                'categoria' : 15,\n" +
                "                'imagen' : 'https://pbs.twimg.com/profile_imagens/1125995646585065474/oJkHqDhY_400x400.png',\n" +
                "                'perfil' : 'Director',\n" +
                "                'nombre' : 'Ruben Gomez',\n" +
                "                'instagram' : '@soyrubengomez',\n" +
                "                'trabajo' : 'gom360'\n" +
                "            }\n" +
                "        ]")

        val firebaseFirestone = FirebaseFirestore.getInstance()
        for (i in 0 until jsonArr.length()){
            val aux = jsonArr.get(i) as JSONObject
            val ponente = Ponentes()
            ponente.nombre=aux.getString("nombre")
            ponente.perfil=aux.getString("perfil")
            ponente.trabajo=aux.getString("trabajo")
            ponente.biografia=aux.getString("biografia")
            ponente.instagram=aux.getString("instagram")
            ponente.imagen=aux.getString("imagen")
            ponente.categoria=aux.getInt("categoria")

            firebaseFirestone.collection("ponentess").document().set(ponente)
        }*/

        val jsonArr2 = JSONArray("[\n" +
                "            {\n" +
                "                \"datetime\" : 1564830000,\n" +
                "                \"descripcion\" : \"Yo les voy a hablar el día de hoy de un tema que si soy exitoso, contrario a lo que algunos de ustedes me conocen saben que juego muchos videojuegos y que trabajo con ellos, toda mi vida llevo 21 años hablando en público a lo largo de la industria de la tecnología y alrededor por supuesto de la oportunidad tremenda que los videojuegos, la realidad virtual y la realidad aumentada ofrecen, sigo convencido en ello y sigo trabajando en eso. Pero estoy aquí con otro sombrero, el día de hoy si soy exitoso van a terminar con dos sentimientos, un poco de miedo y sobretodo el miedo que desemboca en la acción.\",\n" +
                "                \"ponente\" : \"Mario Valle\",\n" +
                "                \"topico\" : \"Negocios\",\n" +
                "                \"titulo\" : \"Ahorrar no te va a salvar del futuro\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564861200,\n" +
                "                \"descripcion\" : \"Tener una marca personal es fundamental para tu desarrollo profesional. Construir un portafolio de proyectos y fortalecer tu presencia online te ayudará a resaltar para ampliar tus oportunidades laborales, conseguir un mejor empleo o crear tu propio negocio.\",\n" +
                "                \"ponente\" : \"Rubén Gómez\",\n" +
                "                \"topico\" : \"Diseño\",\n" +
                "                \"titulo\" : \"¿Tienes una marca personal o solo redes sociales?\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564837200,\n" +
                "                \"descripcion\" : \"Hoy quiero hablarles de los dos tipos de personas que existen en el mundo, hay personas que consumen y hay personas que crean, todos somos de las primeras, todo el tiempo estamos consumiendo, consumimos tecnología, consumimos libros, películas, series, comida, el problemas es que no todo el mundo esta creando y cuando me refiero a crear me refiero a crear productos servicios, eventos, cosas que le aporten algo a la comunidad o al mundo, porque es un problema que no tanta gente este creando como la que esta consumiendo, porque le estamos quitando al mundo mas de lo que le dejamos, no estamos dejando este mundo mejor de como lo encontramos, eso genera consumismo y eso es un problema.\",\n" +
                "                \"ponente\" : \"Cesar Fajardo\",\n" +
                "                \"topico\" : \"Content\",\n" +
                "                \"titulo\" : \"Un 'gracias' vale más que un millón de views\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564826400,\n" +
                "                \"descripcion\" : \"A lo largo de mi presentación les contaré algunas historias para que nunca paren de aprender. Cada semana gracias al equipo increíble de Marveling a mi me encanta contar las noticias porque entender nuestra industria es aprender a dominarla solo cuando entendemos como esta pasando y como las grandes empresas están creando podemos ser parte de ésta transformación entonces algo que me divierte a mi es leer es compartir y es analizar algunas de estas noticias, a veces no hago tan buen trabajo, a veces entiendo 2 o 3 cosas que están pasando con el mundo, pero cada vez más estoy haciendo un esfuerzo porque estas noticias sean más locales.   \",\n" +
                "                \"ponente\" : \"Christian Van Der Henst\",\n" +
                "                \"topico\" : \"Motivacional\",\n" +
                "                \"titulo\" : \"Diseñador, programador, creador y luego emprendedor\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564848000,\n" +
                "                \"descripcion\" : \"Somos una empresa enfocada en el desarrollo de aplicaciones móviles del lado de la ingeniería y tuvimos por primera vez a una diseñadora en el equipo, al principio la veíamos como un adherido al equipo, luego empezamos un proyecto y ahora vengo a comentarles lo que pasó con la comunicación con personas que son de otras áreas.\",\n" +
                "                \"ponente\" : \"Jorge García\",\n" +
                "                \"topico\" : \"Git\",\n" +
                "                \"titulo\" : \"Git, la base de la colaboración mucho más allá del código\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564844400,\n" +
                "                \"descripcion\" : \"Uno de los ejercicios hechos por la mayoría de los ponentes profesionales, para mejorar como dan un mensaje, la premisa es fácil, no poner atención en lo que están diciendo, sino en cómo lo están diciendo y vengo a compartirles algunas técnicas que aprendí.\",\n" +
                "                \"ponente\" : \"Isis García\",\n" +
                "                \"topico\" : \"Voz\",\n" +
                "                \"titulo\" : \"Cómo comunicarte en un mundo saturado de información\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"datetime\" : 1564833600,\n" +
                "                \"descripcion\" : \"El título de la charla se llama Workshops de alto impacto para ideas de alto impacto, yo pienso en mi experiencia personal en el banco, mas bien son Workshops de alto impacto en la vida, yo entré a BBVA hace 3 años, soy diseñadora industrial de la UNAM, empecé como todos con un trabajo pequeño, muy duro encontrar mi primer trabajo y me pagaban una miseria y ustedes debieron vivir algo así, entonces BBVA me dio la oportunidad de entrar a trabajar a un lugar decente y vengo a compartirles acerca de mi experiencia.\",\n" +
                "                \"ponente\" : \"Claudia Sosa\",\n" +
                "                \"topico\" : \"Negocios\",\n" +
                "                \"titulo\" : \"Crear workshops para comprender, idear y decidir\"\n" +
                "            },\n" +

                "            {\n" +
                "                \"datetime\" : 1564822800,\n" +
                "                \"descripcion\" : \"Hoy vamos a hablar de como desarrollar tu carrera profesional pero en tecnología, porque hay otras carreras que tienen menos crecimiento que la carrera de tecnología, cuando ustedes trabajan en tecnología ustedes entran en una carrera que tiene desempleo negativo, una carrera que tiene la demanda más fuerte del mercado, si nosotros vemos los sueldos en los últimos años en tecnología, en el 2013 el desarrollador de software promedio en Latinoamérica ganaba alrededor de 1100 dólares, hoy en el 2018 el desarrollador de software promedio gana 1650 dólares, pero el top 25% de desarrolladores de software ganan 2500 dólares en promedio, normalmente el rango el gradiente de sueldos de los estudiantes de Intrivia que estudian un año o más es de 1000 a 3500 dólares al mes en Latinoamérica.\",\n" +
                "                \"ponente\" : \"Freddy Vega\",\n" +
                "                \"topico\" : \"Motivacional\",\n" +
                "                \"titulo\" : \"Cómo desarrollar tu carrera profesional en tecnología\"\n" +
                "            }\n" +
                "        ]")

        val firebaseFirestone=FirebaseFirestore.getInstance()
        for (i in 0 until jsonArr2.length()){
            val aux=jsonArr2.get(i) as JSONObject
            var conferencia= Conferencia()
            conferencia.titulo=aux.getString("titulo")
            conferencia.descripcion=aux.getString("descripcion")
            conferencia.topico=aux.getString("topico")
            val calendario= Calendar.getInstance()
            calendario.timeInMillis=aux. getLong("datetime")*1000
            conferencia.datetime=calendario.time
            conferencia.ponente=aux.getString("ponente")
            firebaseFirestone.collection("conferenciass").document().set(conferencia)
        }
    }
}