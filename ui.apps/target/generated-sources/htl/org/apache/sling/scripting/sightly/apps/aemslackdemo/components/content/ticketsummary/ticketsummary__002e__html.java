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
package org.apache.sling.scripting.sightly.apps.aemslackdemo.components.content.ticketsummary;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class ticketsummary__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_clientlib = null;
Object _global_ticketitem = null;
Object _global_ticket = null;
Collection var_collectionvar11_list_coerced$ = null;
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
out.write("\n\n\n");
_global_ticketitem = renderContext.call("use", com.aem.slack.core.models.TicketSummary.class.getName(), obj());
out.write("<div></div>\n");
_global_ticket = renderContext.getObjectModel().resolveProperty(_global_ticketitem, "ticketSummary");
if (renderContext.getObjectModel().toBoolean(_global_ticket)) {
}
out.write("\n<div class=\"header\">\n                <h2>\n                    Ticket Summary\n                </h2>\n            </div>\n<div class=\"body\">\n                <div class=\"table-responsive\">\n");
{
    String var_2 = (("<!-- <div>" + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(renderContext.getObjectModel().resolveProperty(_global_ticketitem, "ticketSummary"), "ticketNum"), "comment"))) + "</div> -->");
    out.write(renderContext.getObjectModel().toString(var_2));
}
out.write("\n<table class=\"table table-bordered table-striped table-hover geeks-table dataTable\">\n<tr>\n<td>Ticket Number : </td>\n<td>");
{
    Object var_3 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_ticket, "ticketNum"), "text");
    out.write(renderContext.getObjectModel().toString(var_3));
}
out.write("</td>\n</tr>\n<tr>\n<td>Ticket Description : </td>\n<td>");
{
    Object var_4 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_ticket, "ticketDesc"), "text");
    out.write(renderContext.getObjectModel().toString(var_4));
}
out.write("</td>\n</tr>\n<tr>\n<td>Ticket Raised By : </td>\n<td>");
{
    Object var_5 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_ticket, "raisedBy"), "text");
    out.write(renderContext.getObjectModel().toString(var_5));
}
out.write("</td>\n</tr>\n<tr>\n<td>Ticket Status : </td>\n\n<td>");
{
    Object var_6 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_ticket, "ticketStatus"), "text");
    out.write(renderContext.getObjectModel().toString(var_6));
}
out.write("</td>\n</tr>\n<tr>\n<td>Ticket Assignee : </td> \n<td><input type=\"text\" id=\"assignee\" name=\"assignee\"");
{
    Object var_attrvalue7 = renderContext.getObjectModel().resolveProperty(_global_ticket, "ticketAssignee");
    {
        Object var_attrcontent8 = renderContext.call("xss", var_attrvalue7, "attribute");
        {
            boolean var_shoulddisplayattr10 = (((null != var_attrcontent8) && (!"".equals(var_attrcontent8))) && ((!"".equals(var_attrvalue7)) && (!((Object)false).equals(var_attrvalue7))));
            if (var_shoulddisplayattr10) {
                out.write(" value");
                {
                    boolean var_istrueattr9 = (var_attrvalue7.equals(true));
                    if (!var_istrueattr9) {
                        out.write("=\"");
                        out.write(renderContext.getObjectModel().toString(var_attrcontent8));
                        out.write("\"");
                    }
                }
            }
        }
    }
}
out.write("/> <button id=\"addAssigneeId\" class=\"btn btn-success waves-effect\">Add Assignee</button> </td>\n</tr>\n</table>\n</div>\n</div>\n<div class=\"header\">\n                <h2>\n                    Comment History\n                </h2>\n            </div>\n");
{
    Object var_collectionvar11 = renderContext.getObjectModel().resolveProperty(_global_ticket, "commentList");
    {
        long var_size12 = ((var_collectionvar11_list_coerced$ == null ? (var_collectionvar11_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar11)) : var_collectionvar11_list_coerced$).size());
        {
            boolean var_notempty13 = (var_size12 > 0);
            if (var_notempty13) {
                {
                    long var_end16 = var_size12;
                    {
                        boolean var_validstartstepend17 = (((0 < var_size12) && true) && (var_end16 > 0));
                        if (var_validstartstepend17) {
                            out.write("<div>");
                            if (var_collectionvar11_list_coerced$ == null) {
                                var_collectionvar11_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar11);
                            }
                            long var_index18 = 0;
                            for (Object comment : var_collectionvar11_list_coerced$) {
                                {
                                    boolean var_traversal20 = (((var_index18 >= 0) && (var_index18 <= var_end16)) && true);
                                    if (var_traversal20) {
                                        out.write("\n<div><textarea disabled class=\"commentareaclass\" name=\"commentarea\" id=\"commentarea\" placeholder=\"Please add your comments here\" autocomplete=\"off\" cols=\"160\">");
                                        {
                                            Object var_21 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(comment, "commentText"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_21));
                                        }
                                        out.write("</textarea>\n<div>");
                                        {
                                            String var_22 = (("By : " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(comment, "commentBy"), "text"))) + " ");
                                            out.write(renderContext.getObjectModel().toString(var_22));
                                        }
                                        out.write("</div>\n</div>\n");
                                    }
                                }
                                var_index18++;
                            }
                            out.write("</div>");
                        }
                    }
                }
            }
        }
    }
    var_collectionvar11_list_coerced$ = null;
}
out.write("\n<div><textarea class=\"commentareaclass\" name=\"commentarea\" id=\"commentAreaId\" placeholder=\"Please add your comments here\" autocomplete=\"off\" rows=\"4\" cols=\"160\"></textarea></div>\n\n                <table>\n                <td><div><button class=\"btn btn-success waves-effect\" id=\"commentBtnId\">Add Comment</button> </div></td>\n                <td width=\"20px\">    </td>\n                <td><button id=\"closeBtnId\" class=\"btn btn-success waves-effect\">Close Ticket</button></td></table>\n<div hidden=\"true\" id=\"ticketId\">");
{
    Object var_23 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_ticket, "ticketNum"), "text");
    out.write(renderContext.getObjectModel().toString(var_23));
}
out.write("</div>\n\n<script>\n\n$('#commentBtnId').click(function() {\n        //alert(\"clicked the add comment\");\n        var commentText = $('#commentAreaId').val();\n        //alert(\"commentText:  \"+commentText);\n        var ticketNo = $('#ticketId').text();\n        //alert(\"ticketNo:  \"+ticketNo);\n        var sFilterData = '\"'+ticketNo+'\"__'+commentText;\n\t\t//alert(\"sFilterData:  \"+sFilterData);\n        if(commentText === \"\"){\n\t\t\talert(\"Please provide your comments in the Comment Box\");\n        }else{\n            $.ajax({\n                type: 'POST',\n                    url:'/bin/ticketingsystem/addComment',\n                    data:'sFilterData='+ sFilterData,\n                 success: function(msg){\n                    //alert(\"Response Recieved\"+msg);\n                        if(msg != null){\n                        alert(\"Comment Added Successfully :: \"+msg);\n                        location.reload(true);\n\n                         }\n                    }\n            });\n        }\n    });\n\n\n$('#closeBtnId').click(function() {\n        //alert(\"clicked the add comment\");\n        var commentText = $('#commentAreaId').val();\n        //alert(\"commentText:  \"+commentText);\n        var ticketNo = $('#ticketId').text();\n        //alert(\"ticketNo:  \"+ticketNo);\n        var sFilterData = '\"'+ticketNo+'\"__'+commentText;\n\t\t//alert(\"sFilterData:  \"+sFilterData);\n\n        if(commentText === \"\"){\n\t\t\talert(\"Please provide your comments in the Comment Box before closing the Ticket\");\n        }else{\n\n            $.ajax({\n                type: 'POST',    \n                    url:'/bin/ticketingsystem/closeTicket',\n    \n                    data:'sFilterData='+ sFilterData,\n    \n                 success: function(msg){\n                    //alert(\"Response Recieved\"+msg);\n\n                        if(msg != null){\n                        alert(\"Ticket Closed Successfully :: \"+msg);\n                        location.reload(true);\n\n    \n    \n                         }\n    \n\n                    }\n            });\n\n        }\n\n\n    });\n\n\n$('#addAssigneeId').click(function() {\n       // alert(\"clicked the add comment\");\n\n        var assigneeText = $('#assignee').val();\n        //alert(\"commentText:  \"+commentText);\n        var ticketNo = $('#ticketId').text();\n        //alert(\"ticketNo:  \"+ticketNo);\n        var sFilterData = '\"'+ticketNo+'\"__'+assigneeText;\n\t\talert(\"sFilterData:  \"+sFilterData);\n      //  return;\n        if(assigneeText === \"\"){\n\t\t\talert(\"Please provide your comments in the Comment Box\");\n        }else{\n            $.ajax({\n                type: 'POST',\n                    url:'/bin/ticketingsystem/addAssignee',\n                    data:'sFilterData='+ sFilterData,\n                 success: function(msg){\n                    //alert(\"Response Recieved\"+msg);\n                        if(msg != null){\n                        alert(\"Assignee Added Successfully :: \"+msg);\n                        location.reload(true);\n\n                         }\n                    }\n            });\n        }\n    });\n\n\n</script>");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

