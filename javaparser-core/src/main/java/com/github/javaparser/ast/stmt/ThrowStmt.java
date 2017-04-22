/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2016 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.Range;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithExpression;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import static com.github.javaparser.utils.Utils.assertNotNull;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.ThrowStmtMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;

/**
 * Usage of the throw statement.
 * <br/><code>throw new Exception()</code>
 *
 * @author Julio Vilmar Gesser
 */
public final class ThrowStmt extends Statement implements NodeWithExpression<ThrowStmt> {

    private Expression expression;

    public ThrowStmt() {
        this(null, new NameExpr());
    }

    @AllFieldsConstructor
    public ThrowStmt(final Expression expression) {
        this(null, expression);
    }

    public ThrowStmt(Range range, Expression expression) {
        super(range);
        setExpression(expression);
    }

    @Override
    public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(final VoidVisitor<A> v, final A arg) {
        v.visit(this, arg);
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    public ThrowStmt setExpression(final Expression expression) {
        assertNotNull(expression);
        if (expression == this.expression) {
            return (ThrowStmt) this;
        }
        notifyPropertyChange(ObservableProperty.EXPRESSION, this.expression, expression);
        if (this.expression != null)
            this.expression.setParentNode(null);
        this.expression = expression;
        setAsParentNodeOf(expression);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null)
            return false;
        return super.remove(node);
    }

    @Override
    public ThrowStmt clone() {
        return (ThrowStmt) accept(new CloneVisitor(), null);
    }

    @Override
    public ThrowStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.throwStmtMetaModel;
    }
}
