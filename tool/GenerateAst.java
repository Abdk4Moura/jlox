/*
 *@title: Boring generation of boring boilerplate code
 *@created: <2023-04-15 Sat>
 **/

package tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class GenerateAst {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: generate_ast <output directory>");
            System.exit(64);
        }
        String outputDir = args[0];
        String outputFileName = args[1];
        Pattern invalidCharsPattern = Pattern.compile("[*/ !@#%^&()+|{}<>]");

        boolean matches = invalidChars.matcher(invalidCharsPattern).find();
        if (matches) {
            System.err.println("Error: File name contains invalid character");
            System.exit(60);
        }

        char firstCharOfFileName = outputFileName.charAt(0);
        if (firstCharOfFileName < 'A' || firstCharOfFileName > 'Z') {
            System.err.println("Error: File name must start with uppercase");
            System.exit(56);
        }

        defineAst(outputDir, outputFileName, Arrays.asList("Binary    : Expr left, Token operator, Expr right",
                "Grouping  : Expr expression", "Literal   : Object value", "Unary     : Token operator, Expr right"));
    }

    private static void defineAst(String outputDir, String baseName, List<String> types) throws IOException {
        String path = outputDir + "/" + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);

        writer.println("package com.craftinginterpreters.lox;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println();
        writer.println("abstract class " + baseName + " {");

        defineVisitor(writer, baseName, types);

        // The AST classes.
        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
        }

        // The base accept() method.
        writer.println();
        writer.println("   abstract <R> R accept(Vistor<R> visitor)");

        writer.println("}");
        writer.close();
    }

    private static void defineVisitor(PrintWriter writer, String baseName, List<String> types) {
        writer.println("   interface Visitor<R> {");

        for (String type : types) {
            String typeName = type.split(":")[0].trim();
            writer.println("    R visit" + typeName + baseName + " (" + typeName + " " + baseName.toLowerCase() + ");");
        }
        writer.println("  }");
    }

    private static void defineType(PrintWriter writer, String baseName, String className, String fieldList) {
        writer.println("   static class " + className + " extends " + baseName + " {");

        // Constructor
        writer.println("    " + className + "(" + fieldList + ") {");

        // Store paramters in fields.
        String[] fields = fieldList.split(", ");
        for (String field : fields) {
            String name = field.split(" ")[1];
            writer.println("        this." + name + " = " + name + ";");
        }

        writer.println("}");

        writer.println();
        writer.println("     @Override");
        writer.println("     <R> R accept (Vistor<R> visitor) {");
        writer.println("        return visitor.visit" + className + baseName + "(this);");
        writer.println("    }");

        // Fields.
        writer.println();
        for (String field : fields) {
            writer.println("    final " + field + ";");
        }

        writer.println("}");
    }
}
