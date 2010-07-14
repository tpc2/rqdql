/**
 * RQDQL.com, Requirements Definition and Query Language
 *
 * Redistribution and use in source and binary forms, with or 
 * without modification, are PROHIBITED without prior written 
 * permission from the author. This product may NOT be used 
 * anywhere and on any computer except the server platform of 
 * rqdql.com. located at www.rqdql.com. If you received this 
 * code occasionally and without intent to use it, please report 
 * this incident to the author by email: team@rqdql.com
 *
 * @author Yegor Bugayenko <egor@tpc2.com>
 * @copyright Copyright (c) rqdql.com, 2010
 * @version $Id$
 */

#ifndef __INCLUDE_SOLM_VARIABLE_H
#define __INCLUDE_SOLM_VARIABLE_H

#include <string>
#include <vector>
#include <boost/shared_ptr.hpp>
#include "Solm/Argument.h"
#include "Solm/Chain.h"
#include "Solm/Clauses.h"

namespace solm {

/**
 * Second order logic variable, like "x" or "user"
 */
class Variable : public Argument {

public:
    
    /**
     * Construct a variable
     *
     * @param A string representation of a variable
     */
    Variable(const string& n) : _name(n) { /* that's it */ }

    /**
     * To resolve this argument on some Prolog-like clauses
     * and produce a new Chain of clauses.
     */
    Chain operator+(const Clauses&) const { return Chain(); }

    /**
     * Convert the variable to string
     */
    operator std::string() const { return _name; }

private:
    
    /**
     * Variable name
     */
    std::string _name;

};

}

#endif
