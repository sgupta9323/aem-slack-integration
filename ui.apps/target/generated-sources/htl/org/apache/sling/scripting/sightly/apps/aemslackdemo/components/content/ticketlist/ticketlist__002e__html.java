/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.aemslackdemo.components.content.ticketlist;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class ticketlist__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_clientlib = null;
Object _global_ticketdetails = null;
Object _global_ticketsummarypath = null;
Collection var_collectionvar2_list_coerced$ = null;
out.write("\n");
_global_clientlib = renderContext.call("use", "/libs/granite/sightly/templates/clientlib.html", obj());
out.write("\n    ");
{
    Object var_templatevar0 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "all");
    {
        String var_templateoptions1_field$_categories = "aemslackdemo.components.ticketlist";
        {
            java.util.Map var_templateoptions1 = obj().with("categories", var_templateoptions1_field$_categories);
            callUnit(out, renderContext, var_templatevar0, var_templateoptions1);
        }
    }
}
out.write("\n\n\n<!-- Table -->\n");
_global_ticketdetails = renderContext.call("use", com.aem.slack.core.models.TicketDetails.class.getName(), obj());
out.write("<div></div>\n\n<div class=\"row clearfix\">\n    <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n        <div class=\"card\">\n            <div class=\"header\">\n                <h2>\n                    List Of Employee Tickets\n                </h2>\n            </div>\n            <div class=\"body\">\n                <div class=\"table-responsive\">\n                    <table class=\"table table-bordered table-striped table-hover geeks-table dataTable\">\n                        <thead>\n                        <tr>\n                            <th>Ticket Number</th>\n                            <th>Ticket Description</th>\n                            <th>Raised By</th>\n                            <th>Status</th>\n                            <th>Assigned To</th>\n                            <th>Details</th>\n\n                        </tr>\n                        </thead>\n\n                        <tbody>\n                        ");
_global_ticketsummarypath = renderContext.getObjectModel().resolveProperty(_global_ticketdetails, "nextPagePath");
if (renderContext.getObjectModel().toBoolean(_global_ticketsummarypath)) {
}
out.write("\n                        ");
{
    Object var_collectionvar2 = renderContext.getObjectModel().resolveProperty(_global_ticketdetails, "tickets");
    {
        long var_size3 = ((var_collectionvar2_list_coerced$ == null ? (var_collectionvar2_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar2)) : var_collectionvar2_list_coerced$).size());
        {
            boolean var_notempty4 = (var_size3 > 0);
            if (var_notempty4) {
                {
                    long var_end7 = var_size3;
                    {
                        boolean var_validstartstepend8 = (((0 < var_size3) && true) && (var_end7 > 0));
                        if (var_validstartstepend8) {
                            out.write("<div>");
                            if (var_collectionvar2_list_coerced$ == null) {
                                var_collectionvar2_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar2);
                            }
                            long var_index9 = 0;
                            for (Object ticket : var_collectionvar2_list_coerced$) {
                                {
                                    boolean var_traversal11 = (((var_index9 >= 0) && (var_index9 <= var_end7)) && true);
                                    if (var_traversal11) {
                                        out.write("\n                            <tr>\n                                <td>");
                                        {
                                            Object var_12 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(ticket, "ticketNum"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_12));
                                        }
                                        out.write("</td>\n                                <td>");
                                        {
                                            Object var_13 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(ticket, "ticketDesc"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_13));
                                        }
                                        out.write("</td>\n                                <td>");
                                        {
                                            Object var_14 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(ticket, "raisedBy"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_14));
                                        }
                                        out.write("</td>\n                                <td>");
                                        {
                                            Object var_15 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(ticket, "ticketStatus"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_15));
                                        }
                                        out.write("</td>\n                                <td>");
                                        {
                                            Object var_16 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(ticket, "ticketAssignee"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_16));
                                        }
                                        out.write("</td>\n<td><input type=\"button\" class=\"btn btn-success waves-effect\"");
                                        {
                                            String var_attrcontent17 = (((("location.href='" + renderContext.getObjectModel().toString(renderContext.call("xss", _global_ticketsummarypath, "text"))) + ".html?ticketId=") + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(ticket, "ticketNum"), "scriptString"))) + "'");
                                            out.write(" onclick=\"");
                                            out.write(renderContext.getObjectModel().toString(var_attrcontent17));
                                            out.write("\"");
                                        }
                                        out.write(" value=\"Details\"/></td>\n                                </td>\n                            </tr>\n                        ");
                                    }
                                }
                                var_index9++;
                            }
                            out.write("</div>");
                        }
                    }
                }
            }
        }
    }
    var_collectionvar2_list_coerced$ = null;
}
out.write("\n                        </tbody>\n                    </table>\n                </div>\n            </div>\n        </div>\n    </div>\n</div>\n<!-- #END# Table -->");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

