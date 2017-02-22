package com.tonglu.glossary.glossary.model;

import java.util.ArrayList;

/**
 * Created by tonglu on 2017/1/13.
 */
public class CodeSearchBean {
    public String matchterm;//匹配主体
    public String searchterm;//查询主体
    public int previouspage;
    public int nextpage;
    public int page;
    public ArrayList<CodeSearchItemBean> results;
    public ArrayList<LanguageFiltersBean> language_filters;


//    {
//        "matchterm": "soup",
//            "previouspage": 0,
//            "searchterm": "soup",
//            "query": "soup",
//            "total": 4,
//            "page": 0,
//            "nextpage": 1,
//            "language_filters": [
//        {
//            "count": 3,
//                "id": 3,
//                "language": "HTML"
//        },
//        {
//            "count": 1,
//                "id": 123,
//                "language": "JSON"
//        },
//        {
//            "count": 1,
//                "id": 19,
//                "language": "Python"
//        }
//        ],
//        "source_filters": [
//        {
//            "count": 5,
//                "source": "Github",
//                "id": 2
//        }
//        ],
//        "results": [
//        {
//            "repo": "https://code.google.com/p/html5lib/",
//                "linescount": 239,
//                "location": "/python3/html5lib/treebuilders",
//                "name": "html5lib",
//                "language": "Python",
//                "url": "https://searchcode.com/codesearch/view/24862775/",
//                "md5hash": "b8e6b1d1f53d5e4f87995ebeb35b358c",
//                "lines": {
//            "4": "",
//                    "5": "warnings.warn(\"BeautifulSoup 3.x (as of 3.1) is not fully compatible with html5lib and support will be removed in the future\", DeprecationWarning)",
//                    "6": "",
//                    "7": "from BeautifulSoup import BeautifulSoup, Tag, NavigableString, Comment, Declaration",
//                    "8": "",
//                    "139": "class TextNode(Element):",
//                    "140": "    def __init__(self, element, soup):",
//                    "141": "        _base.Node.__init__(self, None)",
//                    "142": "        self.element = element",
//                    "143": "        self.soup = soup",
//                    "144": "    ",
//                    "150": "        if namespaceHTMLElements:",
//                    "151": "            warnings.warn(\"BeautifulSoup cannot represent elements in any namespace\", DataLossWarning)",
//                    "152": "        _base.TreeBuilder.__init__(self, namespaceHTMLElements)",
//                    "154": "    def documentClass(self):",
//                    "155": "        self.soup = BeautifulSoup(\"\")",
//                    "156": "        return Element(self.soup, self.soup, None)"
//        },
//            "id": 24862775,
//                "filename": "soup.py"
//        }
//        ]
//    }
}
