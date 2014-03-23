/**
 * Copyright (c) 2009-2014, requs.org
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the requs.org nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.requs.ontology;

import com.jcabi.aspects.Loggable;
import java.util.Iterator;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xembly.Directive;
import org.xembly.Directives;

/**
 * Xembly Ontology.
 *
 * @author Yegor Bugayenko (yegor@tpc2.com)
 * @version $Id$
 * @since 1.1
 */
@ToString
@EqualsAndHashCode
@Loggable(Loggable.DEBUG)
public final class XeOntology implements Ontology, Iterable<Directive> {

    /**
     * All directives.
     */
    private final transient Directives dirs = new Directives().add("spec");

    @Override
    public Type type(final String name) {
        this.dirs.xpath("/spec").addIf("types")
            .xpath(String.format("/spec/types[not(type/name='%s')]", name))
            .add("type").add("name").set(name)
            .xpath(String.format("/spec/types/type[name='%s' ]", name));
        return new XeType(
            this.dirs,
            String.format("/spec/types/type[name='%s']", name)
        );
    }

    @Override
    public Method method(final String name) {
        return new XeType(
            this.dirs,
            String.format("/spec/types/type[methods/method/name='%s']", name)
        ).method(name);
    }

    @Override
    public Iterator<Directive> iterator() {
        return this.dirs.iterator();
    }
}
