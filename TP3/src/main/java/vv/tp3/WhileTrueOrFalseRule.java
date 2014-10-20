package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTBlock;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTWhileLoop;
import net.sourceforge.pmd.lang.java.ast.*;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import java.util.List;

/**
 * Created by Thomas & Amona on 20/10/14.
 */
public class WhileTrueOrFalseRule extends AbstractJavaRule{

    public Object visit(ASTWhileStatement node, Object data) {

        ASTExpression expr = node.getFirstChildOfType(ASTExpression.class);
        if (expr != null) {
            ASTPrimaryExpression primExpr = expr.getFirstChildOfType(ASTPrimaryExpression.class);
            if (primExpr != null && primExpr.hasDescendantOfType(ASTBooleanLiteral.class)) {
                ASTStatement st = node.getFirstChildOfType(ASTStatement.class);
                if (!st.hasDescendantOfType(ASTReturnStatement.class) &&
                    !st.hasDescendantOfType(ASTBreakStatement.class)){
                    addViolation(data, node);
                }

            }
        }

        return super.visit(node, data);
    }
}
