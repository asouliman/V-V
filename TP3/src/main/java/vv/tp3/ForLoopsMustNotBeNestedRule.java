package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTForStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * Created by Thomas & Amona on 08/10/14.
 */
public class ForLoopsMustNotBeNestedRule extends AbstractJavaRule {

    public Object visit(ASTForStatement node, Object data) {
        if (hasForLoopChild(node)) {
            addViolation(data, node);
        }

        return super.visit(node,data);
    }

    private boolean hasForLoopChild(Node node) {
        return node.hasDescendantOfType(ASTForStatement.class);
    }
}
