/*
 * The MIT License
 *
 * Copyright (c) 2016, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jenkinsci.plugins.pipeline.milestone;

import hudson.model.Run;
import jenkins.model.CauseOfInterruption;

/**
 * Records that a build was canceled because it reached a milestone but a newer build already passed it, or
 * a newer build {@link Milestone#wentAway(Run)} from the last milestone the build passed.
 */
public final class CancelledCause extends CauseOfInterruption {

    private static final long serialVersionUID = 1;

    private final String newerBuild;

    CancelledCause(Run<?,?> newerBuild) {
        this.newerBuild = newerBuild.getExternalizableId();
    }

    CancelledCause(String newerBuild) {
        this.newerBuild = newerBuild;
    }

    public Run<?,?> getNewerBuild() {
        return Run.fromExternalizableId(newerBuild);
    }

    @Override public String getShortDescription() {
        return "Superseded by " + getNewerBuild().getDisplayName();
    }

}