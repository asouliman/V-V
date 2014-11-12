package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtExecutable;

// Thomas & Amona

public class CountProcessor extends AbstractProcessor<CtExecutable> {

    @Override
    public void process(CtExecutable ctExecutable) {

        // In the case of a default constructor, we do nothing
        if (!ctExecutable.getBody().toString().equals(new String("{\n}"))) {

            SourcePosition sp = ctExecutable.getPosition();
            CompilationUnit compileUnit = sp.getCompilationUnit();

            int position = compileUnit.nextLineIndex(sp.getSourceEnd());

            String snippet = "\t\tvv.spoon.logger.CountWriter.count(\"" + prettify(ctExecutable) + "\");\n";

            SourceCodeFragment after = new SourceCodeFragment(position, snippet, 0);
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
