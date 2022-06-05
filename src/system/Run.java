package system;


import system.business.Employe;
import system.dao.EmployeDAO;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeDAO employeDAO = new EmployeDAO();

        Employe employe = new Employe();

        System.out.println("Bienvenido al sistema de empleados!!:");

        String controlMenu = "s";
        while (controlMenu.equals("s")) {
            System.out.println("[1] para mostrar los empleados registrados");
            System.out.println("[2] para agregar un nuevo empleado");
            System.out.println("[3] para modificar un empleado existente");
            System.out.println("[4] para eliminar un empleado existente");
            System.out.println("Ingrese una de las opciones para continuar:");

            String action = scanner.next();
            String repeat = "s";

            switch (action) {
                case "1":
                    System.out.println(":::::::::::::::::::Lista de Empleados:::::::::::::::::");
                    employeDAO.printEmployes();
                    System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    break;
                case "2":
                    while (repeat.equals("s")) {
                        System.out.println("Ingresa nombre del trabajador:");
                        employe.setName(scanner.next());

                        System.out.println("Ingresa apellidos del trabajador:");
                        employe.setLastName(scanner.next());

                        System.out.println("Ingresa posición del trabajador:");
                        employe.setPosition(scanner.next());

                        if (employeDAO.upsert(employe)) {
                            employeDAO.printEmployes();
                        } else {
                            System.out.println("ha ocurrido un error al intentar guardar el empleado!");
                        }
                        System.out.println("¿desea ingresar un nuevo empleado? [s] para Sí y [n] para No:");
                        repeat = scanner.next();
                    }
                break;
                case "3":
                    while (repeat.equals("s")) {
                        System.out.println("Lista de Empleados:");
                        employeDAO.printEmployes();
                        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");

                        System.out.println("Ingrese ID del emppleado a modificar:");
                        employe.setId(scanner.nextInt());

                        System.out.println("Ingresa nombre del trabajador:");
                        employe.setName(scanner.next());

                        System.out.println("Ingresa apellidos del trabajador:");
                        employe.setLastName(scanner.next());

                        System.out.println("Ingresa posición del trabajador:");
                        employe.setPosition(scanner.next());

                        if (employeDAO.upsert(employe)) {
                            employeDAO.printEmployes();
                        } else {
                            System.out.println("ha ocurrido un error al intentar guardar el empleado!");
                        }
                        System.out.println("¿desea modificar otro empleado? [s] para Sí y [n] para No:");
                        repeat = scanner.next();
                    }
                    break;
                case "4":
                    while (repeat.equals("s")) {
                        System.out.println("Ingresa ID del trabajador:");
                        employe.setId(scanner.nextInt());


                        if (employeDAO.delete(employe)) {
                            employeDAO.printEmployes();
                        } else {
                            System.out.println("ha ocurrido un error al intentar eliminar el empleado!");
                        }
                        System.out.println("¿desea eliminar otro empleado? [s] para Sí y [n] para No:");
                        repeat = scanner.next();
                    }
                    break;
                default:
                    System.out.println("opción invalida!");
                    break;
            }
            System.out.println("ingrese: [s] para volver al menú principal, [n] para salir del sistema:");
            controlMenu = scanner.next();
        }
    }
}
