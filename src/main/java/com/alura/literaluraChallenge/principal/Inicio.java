package com.alura.literaluraChallenge.principal;
import com.alura.literaluraChallenge.model.Libro;
import com.alura.literaluraChallenge.model.LibroClase;
import com.alura.literaluraChallenge.repository.ILibroRepository;
import com.alura.literaluraChallenge.service.ConsumoApi;
import com.alura.literaluraChallenge.service.ConvierteDatos;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.util.stream.Collectors;

public class Inicio {
    Scanner teclado = new Scanner(System.in);
    private ILibroRepository repositorio;
    public Inicio(ILibroRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ******************
                    Elija la opción a través de su número:
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    ******************
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
            }catch(InputMismatchException e) {
                opcion = -1;
            }
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    try {
                        buscarLibroPorTitulo();
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }
    public void buscarLibroPorTitulo() throws JsonProcessingException {
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");

        String input;
        try {
            input = teclado.nextLine();
        }catch (InputMismatchException e){
            input = "#";
        }
        var busqueda = input.replaceAll("[\\W]|_", " ");

        System.out.println(busqueda);

        String url = "https://gutendex.com/books/?search="+busqueda.toLowerCase().replace(" ", "%20");

        ConvierteDatos conversor = new ConvierteDatos();
        var consumoApi = new ConsumoApi();
        var json = consumoApi.obtenerDatos(url);
        var respuesta = conversor.convierteDatos(json);
        System.out.println(respuesta);
        Libro resultado= null;
        LibroClase libroHallado = null;
        Optional<Libro> res = respuesta.listaLibros().stream().findFirst();
        if(res.isPresent()) {
            resultado = res.get();
            System.out.println(resultado.titulo() + " " + resultado.autores() +
                    " " + resultado.idiomas() + " " + resultado.descargas());
            libroHallado = new LibroClase(resultado);
            System.out.println(libroHallado);
            try {
                repositorio.save(libroHallado);
            } catch (RuntimeException e) {
                System.out.println("Libro ya existe");
            }
        }else{
                System.out.println("Libro no hallado");
        }
    }
    public void listarLibrosRegistrados(){
        List <LibroClase> todosLosLibros = repositorio.findAll();
        todosLosLibros.stream().forEach(l-> System.out.println(l));
    }
    public void listarAutoresRegistrados(){
      //  List <LibroClase> todosLosLibros = repositorio.findAll();
      //  todosLosLibros.stream().forEach(a-> System.out.println(a.getAutor()));
        repositorio.listarAutoresRegistrados().stream()
                .forEach(e-> System.out.println("\nAutor: " + e +
                        "\nFecha de nacimiento: "+ repositorio.nacimientoPorAutor(e).get(0).replace("-1","N/A")+
                        "\nFecha de fallecimiento: "+ repositorio.muertePorAutor(e).get(0).replace("-1","N/A")+
                        "\nLibros: " + repositorio.librosPorAutor(e)+"\n"));

        //+ repositorio.librosPorAutor(e)));
       // System.out.println(repositorio.librosPorAutor("Hardy, Thomas"));
    }
    public void listarAutoresVivosPorAno(){
        Integer opcion = -1;

        while(opcion == -1) {
            System.out.println("Ingrese año: ");
            try {
                opcion = teclado.nextInt();
            } catch (InputMismatchException e) {
                opcion = -1;
            }
            teclado.nextLine();
        }

        Integer finalOpcion = opcion;
        List <String> autoresVivos = repositorio.findAll().stream().filter(
                a->(Integer.valueOf(a.getNacimiento()) < finalOpcion) &&
                        (Integer.valueOf(a.getMuerte()) > finalOpcion))
                .map(v->v.getAutor()).collect(Collectors.toList());

        autoresVivos.stream().forEach(e-> System.out.println("\nAutor: " + e +
                "\nFecha de nacimiento: "+ repositorio.nacimientoPorAutor(e).get(0).replace("-1","N/A")+
                "\nFecha de fallecimiento: "+ repositorio.muertePorAutor(e).get(0).replace("-1","N/A")+
                "\nLibros: " + repositorio.librosPorAutor(e)+"\n"));

    }
    public void listarLibrosPorIdioma(){
        System.out.println("""
                           Ingrese el idioma para buscar los libros:
                           es - español
                           en - inglés
                           fr - francés
                           pt - portugués
                           """);
        String idiomoa = "#";
        String [] arrOpciones = {"es","en","fr","pt"};
        while(idiomoa == "#") {
            try {
                idiomoa = teclado.nextLine();
            } catch (InputMismatchException e) {
                idiomoa = "#";
            }
            //teclado.nextLine();
            // boolean test = Arrays.asList(arr)
            //        .contains(toCheckValue);
            if (!Arrays.asList(arrOpciones).contains(idiomoa)) {
                idiomoa = "#";
            }
        }

        List<LibroClase> respuesta = repositorio.librosPorIdioma(idiomoa);
        respuesta.stream().forEach(System.out::println);
    }
}
