package com.springboot.cruddemo.rest;

import com.springboot.cruddemo.CruddemoApplication;
import com.springboot.cruddemo.dao.CompanyRepository;
import com.springboot.cruddemo.dao.EmployeeRepository;
import com.springboot.cruddemo.entity.Company;
import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.CompanyService;
import com.springboot.cruddemo.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CruddemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class CompanyRestControllerTest {

    private List<Company> mockCompanies = new ArrayList<>();
    private List<Employee> mockEmployees = new ArrayList<>();

    public Company c = new Company(1, "TestCompany", "TestBranch");
    public Employee e = new Employee("Test", "Employee", "test@gmail.com", "testDept", "location");

    public List<Company> getMockCompanies() {
        mockCompanies.add(c);
        return mockCompanies;
    }

    public List<Employee> getMockEmployees() {
        e.setCompany(c);
        mockEmployees.add(e);
        return mockEmployees;
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testFindAll() throws Exception {
        Mockito.when(companyRepository.findAll()).thenReturn(getMockCompanies());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/company"
        ).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expectedResult = "[{\"id\":1,\"c_name\":\"TestCompany\",\"c_branch\":\"TestBranch\"}]";

        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetCompany() throws Exception {
        Mockito.when(companyRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(c));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/company/1"
        ).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expectedResult = "{\"id\":1,\"c_name\":\"TestCompany\",\"c_branch\":\"TestBranch\"}";

        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testAddCompany() throws Exception {
        Mockito.when(companyRepository.save(Mockito.any())).thenReturn(c);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(
                "/api/company"
                )
                .content("{\"c_name\":\"TestCompany\",\"c_branch\":\"TestBranch\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expectedResult = "{\"id\":0,\"c_name\":\"TestCompany\",\"c_branch\":\"TestBranch\"}";

        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testDeleteCompany() throws Exception {
        Mockito.when(companyRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(c));
        Mockito.when(employeeRepository.findByCompany(Mockito.any())).thenReturn(getMockEmployees());
        Mockito.when(employeeService.delete(Mockito.any())).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(
                        "/api/company/1"
                )
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expectedResult = "deleted Company id is- 1";

        assert expectedResult.equals(result.getResponse().getContentAsString());
    }

}
