package ca.yorku.cmg.istardt.xmlparser.objects;

public class ANDOperator extends OperatorDecorator {
    public ANDOperator(Formula left, Formula right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String getFormula() {
        return "(" + left.getFormula() + " AND " + right.getFormula() + ")";
    }
}
