package com.mythcon.savr.ngelihwarung.Model;

import java.util.List;

/**
 * Created by SAVR on 27/03/2018.
 */

public class MyResponse {
    public long multicast_id;
    public int success;
    public int failure;
    public int canonical_ids;
    public List<Result> results;
}
