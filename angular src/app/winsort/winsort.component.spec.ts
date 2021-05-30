import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WinsortComponent } from './winsort.component';

describe('WinsortComponent', () => {
  let component: WinsortComponent;
  let fixture: ComponentFixture<WinsortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WinsortComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WinsortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
