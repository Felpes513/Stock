package com.example.demo.Utils;

import org.springframework.format.annotation.NumberFormat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class utils {

    private static final DecimalFormat numberFormat =
            new DecimalFormat("R$ #,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    // Construtor privado para evitar instâncias
    private utils() {
        throw new UnsupportedOperationException("Esta classe não pode ser instanciada.");
    }

    public static String doubleToString(Double value) {
        if (value == null) {
            return "R$ 0,00"; // Retorna um valor padrão se o valor for nulo
        }
        return numberFormat.format(value);
    }
}