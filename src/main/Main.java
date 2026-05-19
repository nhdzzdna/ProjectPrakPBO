package main;

import controller.LoginController;

import view.LoginView;

public class Main {

    public static void main(String[] args) {

        LoginView view =
                new LoginView();

        new LoginController(view);
    }
}
