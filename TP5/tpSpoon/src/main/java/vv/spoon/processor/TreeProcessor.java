package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtExecutable;

// Thomas & Amona

public class TreeProcessor extends AbstractProcessor<CtExecutable> {

    @Override
    public void process(CtExecutable ctExecutable) {

        // In the case of a default constructor, we do nothing
        if (!ctExecutable.getBody().toString().equals(new String("{\n}"))) {

            SourcePosition sp = ctExecutable.getPosition();
            CompilationUnit compileUnit = sp.getCompilationUnit();

            int position = compileUnit.nextLineIndex(sp.getSourceEnd());

            // We increment the scope at the begining of the method
            // and call the append() method of TreeWriter
            String snippet = "\t\tvv.spoon.logger.TreeWriter.incrScope();\n";
            snippet += "\t\tvv.spoon.logger.TreeWriter.append(\"" + prettify(ctExecutable) + "\");\n";
            SourceCodeFragment after = new SourceCodeFragment(position, snippet, 0);
            compileUnit.addSourceCodeFragment(after);

            // We decrement the scope at the end of the method
            CtStatement lastStmt = ctExecutable.getBody().getLastStatement();
            sp = lastStmt.getPosition();
            compileUnit = sp.getCompilationUnit();
            position = compileUnit.nextLineIndex(sp.getSourceEnd());
            snippet = "\n\t\tvv.spoon.logger.TreeWriter.decrScope();";

            // If there is a return statement, we decrement the scope before that statement
            if (lastStmt instanceof CtReturn) {
                position = compileUnit.beginOfLineIndex(sp.getSourceStart());
                snippet = "\t\tvv.spoon.logger.TreeWriter.decrScope();\n";
            }
            after = new SourceCodeFragment(position, snippet, 0);
            compileUnit.addSourceCodeFragment(after);
        }
    }

    private String prettify(CtExecutable ctExecutable) {

        String _class = ctExecutable.getDeclaringType().getSimpleName();
        String method = (ctExecutable.getSimpleName().equals("<init>") ? _class : ctExecutable.getSimpleName());
        String args = "";
        for (Object arg : ctExecutable.getParameters()) {
            args += arg + ", ";
        }
        args = args.substring(0, (args.length() == 0) ? 0 : args.length() - 2);

        return _class + "." + method + "(" + args + ")";
    }

}
